<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="产品id" prop="productId">
        <el-input
          v-model="queryParams.productId"
          placeholder="请输入产品id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品父id" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入产品父id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择产品状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
        >搜索</el-button>
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['test:product:add']"
        >新增</el-button>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="productList"
      row-key="productId"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="产品id" align="center" prop="productId" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="产品父id" align="center" prop="parentId" />
      <el-table-column label="排列顺序" align="center" prop="orderNum" />
      <el-table-column label="产品状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['test:product:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['test:product:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改产品管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品id" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入产品id" />
        </el-form-item>
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="产品父id" prop="parentId">
          <treeselect v-model="form.parentId" :options="productOptions" :normalizer="normalizer" placeholder="请选择产品父id" />
        </el-form-item>
        <el-form-item label="排列顺序" prop="orderNum">
          <el-input v-model="form.orderNum" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="产品状态">
          <el-select v-model="form.status" placeholder="请选择产品状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listProduct, getProduct, delProduct, addProduct, updateProduct, exportProduct } from "@/api/test/product";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: "Product",
    components: { Treeselect },
    data() {
      return {
        // 遮罩层
        loading: true,
        // 产品管理表格数据
        productList: [],
        // 产品管理树选项
        productOptions: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 产品状态字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          productId: undefined,
          productName: undefined,
          parentId: undefined,
          orderNum: undefined,
          status: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          productId: [
            { required: true, message: "产品id不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("test_product_status").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      /** 查询产品管理列表 */
      getList() {
        this.loading = true;
        listProduct(this.queryParams).then(response => {
          this.productList = this.handleTree(response.data, "productId", "parentId");
          this.loading = false;
        });
      },
      /** 转换产品管理数据结构 */
      normalizer(node) {
        if (node.children && !node.children.length) {
          delete node.children;
        }
        return {
          id: node.productId,
          label: node.productName,
          children: node.children
        };
      },
      /** 查询部门下拉树结构 */
      getTreeselect() {
        listProduct().then(response => {
          this.productOptions = [];
          const data = { productId: 0, productName: '顶级节点', children: [] };
          data.children = this.handleTree(response.data, "productId", "parentId");
          this.productOptions.push(data);
        });
      },
      // 产品状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          productId: undefined,
          productName: undefined,
          parentId: undefined,
          orderNum: undefined,
          status: undefined
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.getTreeselect();
        this.open = true;
        this.title = "添加产品管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.getTreeselect();
        if (row != undefined) {
          this.form.parentId = row.productId;
        }
        getProduct(row.productId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改产品管理";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.productId != undefined) {
              updateProduct(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addProduct(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                }
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$confirm('是否确认删除产品管理编号为"' + row.productId + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProduct(row.productId);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      }
    }
  };
</script>
