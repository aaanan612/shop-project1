<template>
  <div class="app-container">
    <el-col :span="4" :xs="24">

      <div class="head-container">
        <el-tree
          :data="tree"
          :props="defaultProps"
          :expand-on-click-node="false"
          ref="tree"
          default-expand-all
          @node-click = "handleNodeClick"
        />
      </div>
    </el-col>
    <el-col :span="20" :xs="24">
      <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
        <el-form-item label="年级成员姓名" prop="memberName">
          <el-input
            v-model="queryParams.memberName"
            placeholder="请输入年级成员姓名"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="年级编号" prop="gradeId">
          <el-input
            v-model="queryParams.gradeId"
            placeholder="请输入年级编号"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="成员状态" prop="memberStatus">
          <el-select v-model="queryParams.memberStatus" placeholder="请选择成员状态" clearable size="small">
            <el-option
              v-for="dict in memberStatusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['test:member:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['test:member:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['test:member:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['test:member:export']"
          >导出</el-button>
        </el-col>
      </el-row>

      <el-table v-loading="loading" :data="memberList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="成员编号" align="center" prop="memberId" />
        <el-table-column label="年级成员姓名" align="center" prop="memberName" />
        <el-table-column label="年级编号" align="center" prop="gradeId" />
        <el-table-column label="成员状态" align="center" prop="memberStatus" :formatter="memberStatusFormat" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['test:member:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['test:member:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-col>
    <!-- 添加或修改年纪成员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="年级成员姓名" prop="memberName">
          <el-input v-model="form.memberName" placeholder="请输入年级成员姓名" />
        </el-form-item>
        <el-form-item label="年级编号" prop="gradeId">
          <el-input v-model="form.gradeId" :placeholder="this.gradeId" :value="this.gradeId" readonly = "readonly" />
        </el-form-item>
        <el-form-item label="成员状态">
          <el-radio-group v-model="form.memberStatus">
            <el-radio
              v-for="dict in memberStatusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
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
  import { listMember, getMember, delMember, addMember, updateMember, exportMember,getMemberByGradeId } from "@/api/test/member";

  export default {
    name: "Member",
    data() {
      return {
        gradeId:0,
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 总条数
        total: 0,
        // 年纪成员表格数据
        memberList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 成员状态字典
        memberStatusOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          memberName: undefined,
          gradeId: undefined,
          memberStatus: undefined
        },
        // 表单参数
        form: {},

        // 表单校验
        rules: {
        },
//树形数据
        tree: [{
          id: 1,
          label: '功能 1',
          children: [{
            id: 4,
            label: '功能 1-1',
          }]
        }, {
          id: 2,
          label: '功能 2',
          children: [{
            id: 5,
            label: '功能 2-1'
          }, {
            id: 6,
            label: '功能 2-2'
          }]
        }, {
          id: 3,
          label: '功能 3',
          children: [{
            id: 7,
            label: '功能 3-1'
          }, {
            id: 8,
            label: '功能 3-2'
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("test_product_status").then(response => {
        this.memberStatusOptions = response.data;
      });
    },
    watch:{
      // tree(newName, oldName){
      //   console.log(newName,oldName)
      // },
      // $router(to,from){
      //   console.log(to.path);
      // }

    },
    //点击事件
    methods: {
      handleNodeClick(){
        this.$router.push({
          path:"/system/user"
        })
      },

      /** 查询年纪成员列表 */
      getList() {
        this.gradeId = this.$route.query.gradeId
        alert(this.gradeId);
        this.loading = true;
        getMemberByGradeId(this.gradeId).then(response => {
          this.memberList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 成员状态字典翻译
      memberStatusFormat(row, column) {
        return this.selectDictLabel(this.memberStatusOptions, row.memberStatus);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          memberId: undefined,
          memberName: undefined,
          gradeId: undefined,
          memberStatus: "0"
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.memberId)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加年纪成员";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const memberId = row.memberId || this.ids
        getMember(memberId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改年纪成员";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.memberId != undefined) {
              updateMember(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              this.form.gradeId = this.gradeId;
              addMember(this.form).then(response => {
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
        const memberIds = row.memberId || this.ids;
        this.$confirm('是否确认删除年纪成员编号为"' + memberIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMember(memberIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有年纪成员数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMember(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
