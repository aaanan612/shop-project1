<template>
  <el-row :gutter="10" class="mb8">
    <el-col :span="1.5">
      <el-button
        type="primary"
        icon="el-icon-plus"
        size="mini"
        @click="handleAdd"
        v-hasPermi="[addPermit]"
      >新增</el-button>
    </el-col>
    <el-col :span="1.5">
      <el-button
        type="success"
        icon="el-icon-edit"
        size="mini"
        :disabled="single"
        @click="handleUpdate"
        v-hasPermi="[editPermit]"
      >修改</el-button>
    </el-col>
    <el-col :span="1.5">
      <el-button
        type="danger"
        icon="el-icon-delete"
        size="mini"
        :disabled="multiple"
        @click="handleDelete"
        v-hasPermi="[removePermit]"
      >删除</el-button>
    </el-col>
    <el-col :span="1.5">
      <el-button
        type="warning"
        icon="el-icon-download"
        size="mini"
        @click="handleExport"
        v-hasPermi="[exportPermit]"
      >导出</el-button>
    </el-col>
    <!--导入按钮-->
    <el-button
      type="info"
      icon="el-icon-upload2"
      size="mini"
      @click="handleImport"
      v-hasPermi="[importPermit]"
    >导入</el-button>
  </el-row>
</template>
<script>
  export default {
    name: "add_component",
    componentName: "add_component",
    props: {
      single:{
        type: Boolean
      },
      multiple:{
        type: Boolean
      },
      hasPermi: {
        type: String,
        default:''
      },
      handleAdd:{
        type: Function
      },
      handleUpdate:{
        type: Function
      },
      handleDelete:{
        type: Function
      },
      handleExport:{
        type: Function
      },
      handleImport:{
        type: Function
      }

    },
    data() {
      return {
        addPermit: this.hasPermi + ':add',
        editPermit: this.hasPermi + ':edit',
        removePermit: this.hasPermi + ':remove',
        exportPermit: this.hasPermi + ':export',
        importPermit: this.hasPermi + ':import'
      }
    },
  };

</script>
