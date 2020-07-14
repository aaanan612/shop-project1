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
          @node-click = "handleNodeClick()"
        />
      </div>
    </el-col>
    <div v-show="person_message">
      <el-form ref="elForm" :v-model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="20" :xs="24">
          <el-form-item label="姓名">
            <el-input v-model="formData.name" name = "name" placeholder="请输入姓名" clearable :style="{width: '20%'}">
            </el-input>
          </el-form-item>
          <br>
          <el-form-item label="性别">
            <el-input v-model="formData.sex" name = "sex" placeholder="请输入性别" clearable :style="{width: '20%'}">
            </el-input>
          </el-form-item>
          <br>
          <el-form-item label="籍贯">
            <el-input v-model="formData.healthMessage" placeholder="请输入健康信息" clearable :style="{width: '20%'}">
            </el-input>
          </el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <!--          <el-button type="primary" @click="editForm">修改</el-button>-->
          <!--          <el-button @click="resetForm">重置</el-button>-->
        </el-col>

      </el-form>
    </div>

    <div v-show="study_message">
      <el-form ref="elForm" :v-model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="20" :xs="24">
          <el-form-item label="学习信息">
            <el-input v-model="formData.studyMessage" placeholder="请输入学习信息" clearable :style="{width: '20%'}">
            </el-input>
          </el-form-item>
          <br>
          <el-form-item label="生日">
            <el-input v-model="formData.birthday" placeholder="请输入生日" clearable :style="{width: '20%'}">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
              </template>
            </el-input>
          </el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <!--          <el-button type="primary" @click="editForm">修改</el-button>-->
          <!--          <el-button @click="resetForm">重置</el-button>-->
        </el-col>

      </el-form>
    </div>
    <div v-show="job_message">
      <el-form ref="elForm" :v-model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="20" :xs="24">
          <el-form-item label="工作信息">
            <el-input v-model="formData.jobMessage" placeholder="请输入工作信息" clearable :style="{width: '20%'}">
            </el-input>
          </el-form-item>

          <el-button type="primary" @click="submitForm">提交</el-button>
          <!--          <el-button type="primary" @click="editForm">修改</el-button>-->
          <!--          <el-button @click="resetForm">重置</el-button>-->
        </el-col>

      </el-form>
    </div>

  </div>


</template>
<script>
  import { listMessage, getMessage, delMessage, addMessage, updateMessage, exportMessage } from "@/api/system/message";
  export default{
    name: "Message_component",
    created() {
      const messageId = this.$route.params.messageId;
      //如果传递有参数，即为修改
      if(messageId){
        //回显数据，将id固定为之前的
        getMessage(messageId).then(response => {
          this.formData = response.data;
          this.formData.messageId = messageId;
        });
      }else{
        this.reset();
      }


    },
    data(){
      return {
        //个人信息
        person_message:true,
        //学习信息
        study_message:false,
        //工作信息
        job_message:false,

        formData:{},

        tree: [{
          id: 1,
          label: '信息',
          children: [{
            id: 1-1,
            message:this.study_message,
            label: '个人信息',
          },{
            id: 1-2,
            message:this.study_message,
            label: '学习信息',
          },{
            id: 1-3,
            message:this.job_message,
            label: '工作信息',
          }
          ]
        },{
            id: 2,
            label: '进度查看',
        }
        ],
        defaultProps: {
          children: 'children',
          label: 'label',
        }
      }

    },

    methods:{
      handleNodeClick(data){
        const currentLabel = this.$refs.tree.getCurrentNode().label;
        if(currentLabel=="个人信息"){
          this.person_message = true;
          this.study_message = false;
          this.job_message = false;
        }else if(currentLabel=="学习信息"){
          this.study_message = true;
          this.person_message = false;
          this.job_message = false;
        }else if(currentLabel=="工作信息"){
          this.study_message = false;
          this.person_message = false;
          this.job_message = true;
        }

      },
      //提交按钮
      submitForm: function() {
        this.$refs["elForm"].validate(valid => {
          if (valid) {
            if (this.formData.messageId != undefined) {
              updateMessage(this.formData).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                }
              });
            } else {
              addMessage(this.formData).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.reset()
                }
              });
            }
          }
        });
      },
      reset() {
        this.formData = {
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
      resetForm() {
        this.$refs['elForm'].resetFields();

      }
    }
  }
</script>
