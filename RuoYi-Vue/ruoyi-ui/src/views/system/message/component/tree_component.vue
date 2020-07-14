<template>
  <!--树形控件-->
  <div class="head-container">
    <el-col :span="span" :xs="xs">
      <el-tree
        :data="tree"
        :props="defaultProps"
        :expand-on-click-node="expandOnClickNode"
        default-expand-all
        @node-click = "handleNodeClick"
      />
    </el-col>
  </div>
</template>
<script>
  export default {
    name: 'Tree',
    componentName:'Tree',
    props: {
      // 树形结构
      tree:{
        type:Array
      },
      //被选中的树形信息
      selectedFormMessage: {
        type:String
      },
      //父组件点击树形，更改data数据方法
      selectedChange: {
        type:Function
      },

      span: {
        type:Number,
        default:4
      },
      xs: {
        type:Number,
        default:24
      },
      //是否在点击节点的时候展开或者收缩节点
      expandOnClickNode:{
        type:Boolean,
        default:true
      },
    },
    data() {
      return {
        //被选中部分的表单名
        selectedMessage:this.selectedFormMessage,
        //树形结构属性值
        defaultProps:{
          children: 'children',
          label: 'label',
          message: 'message'
        }
      }
    },
    methods: {
      //点击调用方法
      handleNodeClick(data) {
          this.selectedMessage = data.message;
          this.selectedChange();

      }
    }
  }
</script>
