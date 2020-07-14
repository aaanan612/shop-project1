<template>

</template>
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
    <el-button
      type="info"
      icon="el-icon-upload2"
      size="mini"
      @click="handleImport"
      v-hasPermi="['system:student:import']"
    >导入</el-button>
  </el-col>
</el-row>

<script>
  export default {
    name: 'Code_component',
    data() {
      return {
        // 非多个禁用
        multiple: true
      };
    },
    methods: {
      /** 查询學生列表 */

    }
  }
</script>
