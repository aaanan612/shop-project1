<template>
  <div class="app-container">
    <div v-show="page1">
      <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
        <el-form-item label="姓名" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入姓名"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable size="small">
            <el-option
              v-for="dict in sexOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="queryParams.birthday"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择生日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="工作信息" prop="jobMessage">
          <el-input
            v-model="queryParams.jobMessage"
            placeholder="请输入工作信息"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="学习信息" prop="studyMessage">
          <el-input
            v-model="queryParams.studyMessage"
            placeholder="请输入学习信息"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="健康信息" prop="healthMessage">
          <el-input
            v-model="queryParams.healthMessage"
            placeholder="请输入健康信息"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
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
            v-hasPermi="['system:message:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['system:message:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:message:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:message:export']"
          >导出</el-button>
        </el-col>
      </el-row>

      <el-table v-loading="loading" :data="messageList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="个人编号" align="center" prop="id" />
        <el-table-column label="姓名" align="center" prop="name" />
        <el-table-column label="性别" align="center" prop="sex" :formatter="sexFormat" />
        <el-table-column label="生日" align="center" prop="birthday" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="工作信息" align="center" prop="jobMessage" />
        <el-table-column label="学习信息" align="center" prop="studyMessage" />
        <el-table-column label="健康信息" align="center" prop="healthMessage" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:message:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:message:remove']"
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
    </div>


    <!-- 添加或修改信息页面展示 -->
    <div v-show="page2">
      <div class="app-container">
        <tree-component :tree = "tree" :selectedMessage = "selectedMessage" :selectedChange = "selectedChange" ref = "tree1"></tree-component>
        <el-col :span="4" :xs="24">
          <!--表单展示-->
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <div v-show="selectedMessage=='person_message'">
          <el-form-item label="姓名">
            <el-input v-model="form.name" placeholder="请输入姓名" />
          </el-form-item>
            <br>
          <el-form-item label="性别">
            <el-select v-model="form.sex" placeholder="请选择性别">
              <el-option
                v-for="dict in sexOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
            <br>
          <el-form-item label="生日">
            <el-date-picker clearable size="small" style="width: 200px"
                            v-model="form.birthday"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择生日">
            </el-date-picker>
          </el-form-item>
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="backForm">返回信息页</el-button>
          </div>
          <div v-show="selectedMessage=='job_message'">
          <el-form-item label="工作信息">
            <el-input v-model="form.studyMessage" placeholder="请输入工作信息" />
          </el-form-item>
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="backForm">返回信息页</el-button>
          </div>
          <div v-if="selectedMessage=='study_message'">
          <el-form-item label="学习信息">
            <el-input v-model="form.jobMessage" placeholder="请输入学习信息" />
          </el-form-item>
            <br>
          <el-form-item label="健康信息">
            <el-input v-model="form.healthMessage" placeholder="请输入健康信息" />
          </el-form-item>
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="backForm">返回信息页</el-button>
          </div>
        </el-form>
        </el-col>
      </div>
    </div>
  </div>
</template>

<script>
  import { listMessage, getMessage, delMessage, addMessage, updateMessage, exportMessage } from "@/api/system/message";
  import Tree from '@/api/test/tree_component'
  export default {
    name: "Message",
    components:{Tree},
    data() {
      return {
        //选中元素
        selectedMessage:'person_message',
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
        // 信息表格数据
        messageList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 性别字典
        sexOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: undefined,
          sex: undefined,
          birthday: undefined,
          jobMessage: undefined,
          studyMessage: undefined,
          healthMessage: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        },
        //第一页信息
        page1:true,
        //第二页信息
        page2:false,
        //树形信息
        tree: [{
          id: 1,
          message:'person_message',
          label: '个人信息',
          children: [{
            id: 1-1,
            message:'study_message',
            label: '学习信息',
          },{
            id: 1-2,
            message:'job_message',
            label: '工作信息',
          }
          ]
        }]
      };
    },

    created() {
      this.getList();
      this.getDicts("sys_user_sex").then(response => {
        this.sexOptions = response.data;
      });
    },
    methods: {
      selectedChange(){
        this.selectedMessage = this.$refs.tree1.selectedMessage;
        alert(this.selectedMessage)
      },
      backForm(){
        this.page2 = false;
        this.page1=true;
        this.getList();
      },
      /** 查询信息列表 */
      getList() {
        this.loading = true;
        listMessage(this.queryParams).then(response => {
          this.messageList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 性别字典翻译
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
          birthday: undefined,
          jobMessage: undefined,
          studyMessage: undefined,
          healthMessage: undefined
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
        this.reset();
        this.page1 = false;
        this.page2 = true;
        this.open = true;
        this.title = "添加信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.page1 = false;
        this.page2 = true;
        const id = row.id || this.ids
        getMessage(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改信息";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateMessage(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                }
              });
            } else {
              addMessage(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.open = false;
                }

              });
            }
            this.reset();
            this.page1 = true;
            this.page2 = false;
            this.getList();
          }

        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMessage(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMessage(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
