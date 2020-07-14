<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="學生姓名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入學生姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="學生年齡" prop="studentAge">
        <el-input
          v-model="queryParams.studentAge"
          placeholder="请输入學生年齡"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="興趣愛好" prop="studentHobby">
        <el-input
          v-model="queryParams.studentHobby"
          placeholder="请输入興趣愛好"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="學生性別" prop="studentSex">
        <el-select v-model="queryParams.studentSex" placeholder="请选择學生性別" clearable size="small">
          <el-option
            v-for="dict in studentSexOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="狀態" prop="studentStatus">
        <el-input
          v-model="queryParams.studentStatus"
          placeholder="请输入狀態"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生日" prop="studentBirthday">
        <el-date-picker clearable size="small" style="width: 200px"
                        v-model="queryParams.studentBirthday"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择生日">
        </el-date-picker>
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
          v-hasPermi="['system:student:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:student:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:student:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:student:export']"
        >导出</el-button>

<!--导入按钮-->
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['system:student:import']"
        >导入</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="學生id" align="center" prop="studentId" />
      <el-table-column label="學生姓名" align="center" prop="studentName" />
      <el-table-column label="學生年齡" align="center" prop="studentAge" />
      <el-table-column label="興趣愛好" align="center" prop="studentHobby" />
      <el-table-column label="學生性別" align="center" prop="studentSex" :formatter="studentSexFormat" />
      <el-table-column label="狀態" align="center" prop="studentStatus" />
      <el-table-column label="生日" align="center" prop="studentBirthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.studentBirthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="備注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:student:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"


            v-hasPermi="['system:student:remove']"
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

    <!-- 添加或修改學生对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="學生姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入學生姓名" />
        </el-form-item>
        <el-form-item label="學生年齡" prop="studentAge">
          <el-input v-model="form.studentAge" placeholder="请输入學生年齡" />
        </el-form-item>
        <el-form-item label="興趣愛好" prop="studentHobby">
          <el-input v-model="form.studentHobby" placeholder="请输入興趣愛好" />
        </el-form-item>
        <el-form-item label="學生性別">
          <el-select v-model="form.studentSex" placeholder="请选择學生性別">
            <el-option
              v-for="dict in studentSexOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="狀態" prop="studentStatus">
          <el-input v-model="form.studentStatus" placeholder="请输入狀態" />
        </el-form-item>
        <el-form-item label="生日" prop="studentBirthday">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="form.studentBirthday"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择生日">
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
  </div>
</template>

<script>
    //导入文件
  // import { listStudent, getStudent, delStudent, addStudent, updateStudent, exportStudent } from "@/api/system/student";
  import { listStudent, getStudent, delStudent, addStudent, updateStudent, exportStudent ,importTemplate} from "@/api/system/student";
    import { getToken } from "@/utils/auth";
  export default {
    name: "Student",
    data() {
      return {
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
        // 學生表格数据
        studentList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 學生性別字典
        studentSexOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          studentName: undefined,
          studentAge: undefined,
          studentHobby: undefined,
          studentSex: undefined,
          studentStatus: undefined,
          studentBirthday: undefined,
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
          studentName: [
            { required: true, message: "學生姓名不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_user_sex").then(response => {
        this.studentSexOptions = response.data;
      });
    },
    methods: {
      /** 查询學生列表 */
      getList() {
        this.loading = true;
        listStudent(this.queryParams).then(response => {
          this.studentList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 學生性別字典翻译
      studentSexFormat(row, column) {
        return this.selectDictLabel(this.studentSexOptions, row.studentSex);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          studentId: undefined,
          studentName: undefined,
          studentAge: undefined,
          studentHobby: undefined,
          studentSex: undefined,
          studentStatus: undefined,
          studentBirthday: undefined,
          remark: undefined
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
        this.ids = selection.map(item => item.studentId)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加學生";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const studentId = row.studentId || this.ids
        getStudent(studentId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改學生";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.studentId != undefined) {
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
        const studentIds = row.studentId || this.ids;
        this.$confirm('是否确认删除學生编号为"' + studentIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStudent(studentIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有學生数据项?', "警告", {
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

