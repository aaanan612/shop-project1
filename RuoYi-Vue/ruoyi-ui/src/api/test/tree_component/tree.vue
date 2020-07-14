<template>
      <el-col :span="4" :xs="24">
        <!--树形控件-->
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
</template>
<script>
  export default {
    name: 'Tree',
    props:['tree_data'],
    data() {
      return {
        // 树形结构
        tree: this.tree_data,
        //被选中部分的表单名
        selectedFormMessage: '',
        //树形结构属性值
        defaultProps: {
          children: 'children',
          label: 'label',
          message: ''
        }
      }
    },
    methods: {
      handleNodeClick(data) {
        this.selectedFormMessage = data.message;
      }
    }
  }
</script>
