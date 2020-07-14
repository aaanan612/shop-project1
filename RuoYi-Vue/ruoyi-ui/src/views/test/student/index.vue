<template>
  <div class="app-container">
    <div>
      <tree-component
        :tree = "tree"
        :selectedMessage = "selectedMessage"
        :selectedChange  = "selectedChange"
        ref="tree">
      </tree-component>
    </div>
    <el-col :span="20" :xs="24">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="学生姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入学生姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生年龄" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择学生年龄" clearable size="small">
          <el-option
            v-for="dict in sexOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="学生生日" prop="birthday">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.birthday"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择学生生日">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
      <add-component
        :hasPermi = "'test:student'"
        :handleAdd = "handleAdd"
        :handleUpdate = "handleUpdate"
        :handleDelete = "handleDelete"
        :handleExport = "handleExport"
        :handleImport = "handleImport"
        :single = "single"
        :multiple = "multiple"
        ref="add"
      >
      </add-component>
    <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生编号" align="center" prop="id" />
      <el-table-column label="学生姓名" align="center" prop="name" />
      <el-table-column label="学生年龄" align="center" prop="sex" :formatter="sexFormat" />
      <el-table-column label="学生生日" align="center" prop="birthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['test:student:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['test:student:remove']"
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

    <!-- 添加或修改学生对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入学生姓名" />
        </el-form-item>
        <el-form-item label="学生年龄">
          <el-select v-model="form.sex" placeholder="请选择学生年龄">
            <el-option
              v-for="dict in sexOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学生生日" prop="birthday">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.birthday"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择学生生日">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
              ref="upload"
              :limit="1"
              accept=".xlsx, .xls"
              :headers="upload.headers"
              :action="upload.url + '?updateSupport=' + upload.updateSupport"
              :disabled="upload.isUploading"
              :on-progress="handleFileUploadProgress"
              :on-success="handleFileSuccess"
              :auto-upload="false"
              drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的用户数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
    </el-col>
  </div>
</template>

<script>
  //导入文件
  import { listStudent, getStudent, delStudent, addStudent, updateStudent, exportStudent ,importTemplate} from "@/api/test/student";
  import { getToken } from "@/utils/auth";
  import Tree from '@/api/test/tree_component'
export default {
  name: "Student",
  components:{Tree},
  data() {
    return {
      selectedMessage:'',
      tree:[{
        label: '一级 1',
        children: [{
          label: '二级 1-1',
          message:'https://blog.csdn.net/l1996729/article/details/106354377/',
          children: [{
            label: '三级 1-1-1',
            message:'https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%20Props%20with%20type%20Object%2FArray%20must%20use%20a%20factory%20function%20to%20return%20the%20default%20value.&fenlei=256&oq=idea%25E5%2585%25A8%25E5%25B1%2580%25E6%259F%25A5%25E6%2589%25BE&rsv_pq=bd0fe2e5000afbd2&rsv_t=abafS5J2Onc5jdI7kYn1XaTbM4DwIYrd8MxHSktFeXXKil7ujRPXZl%2B8qc4&rqlang=cn&rsv_enter=0&rsv_dl=tb&rsv_btype=t&inputT=1679&rsv_sug3=165&rsv_sug1=123&rsv_sug7=100&rsv_n=2&rsv_sug4=1790',
          }]
        }]
      }, {
        label: '一级 1',
        children: [{
          label: '二级 1-1',
          message:'https://blog.csdn.net/l1996729/article/details/106354377/',
          children: [{
            label: '三级 1-1-1',
            message:'https://blog.csdn.net/l1996729/article/details/106354377/'
          }]
        }]
      }],
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
      // 学生表格数据
      studentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 学生年龄字典
      sexOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        sex: undefined,
        birthday: undefined
      },

      //上传数据
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/student/importData"
      },

      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    // alert(this.$refs['tree1'].children);
    this.getList();
    this.getDicts("sys_student_sex").then(response => {
      this.sexOptions = response.data;
    });
  },
  methods: {
    selectedChange (){
      const message = this.$refs.tree.selectedMessage;
      alert(message);
      window.location.href = message;
    },
    /** 查询学生列表 */
    getList() {
      this.loading = true;
      listStudent(this.queryParams).then(response => {
        this.studentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 学生年龄字典翻译
    sexFormat(row, column) {
      return this.selectDictLabel(this.sexOptions, row.sex);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        sex: undefined,
        birthday: undefined
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      alert(this.$refs.add.addPermit);
      this.reset();
      this.open = true;
      this.title = "添加学生";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStudent(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateStudent(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addStudent(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除学生编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStudent(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有学生数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportStudent(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    //  导入方法
    /** 导入按钮操作 */
    handleImport(){
      this.upload.title = "学生导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      importTemplate().then(response => {
        this.download(response.msg);
      });
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList){
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList){
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>
