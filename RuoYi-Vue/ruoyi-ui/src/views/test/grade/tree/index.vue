<template>
<!--  部门tree-->
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-tree
            :data="data"
            show-checkbox
            node-key="id"
            :default-expanded-keys="[2, 3]"
            :default-checked-keys="[5]"
            :props="defaultProps"
            @node-click = "handleNodeClick()"
          >
          </el-tree>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<!--<script>-->
<!--  import { treeselect} from "@/api/test/tree";-->
<!--  import Treeselect from "@riophae/vue-treeselect";-->
<!--  import "@riophae/vue-treeselect/dist/vue-treeselect.css";-->

<!--  export default {-->
<!--    name: "Tree",-->
<!--    components: {Treeselect},-->
<!--    watch: {-->
<!--      // 根据名称筛选部门树-->
<!--      deptName(val) {-->
<!--        this.$refs.tree.filter(val);-->
<!--      }-->
<!--    },-->
<!--    //获取tree-->
<!--    created() {-->
<!--      this.getTreeselect();-->
<!--      const gradeId = this.$router.params.gradeId;-->
<!--    },-->
<!--    methods: {-->
<!--      getTreeselect() {-->
<!--        treeselect().then(response => {-->
<!--          this.deptOptions = response.data;-->
<!--        });-->
<!--      },-->
<!--      // 筛选节点-->
<!--      filterNode(value, data) {-->
<!--        if (!value) return true;-->
<!--        return data.label.indexOf(value) !== -1;-->
<!--      },-->
<!--      // 节点单击事件-->
<!--      handleNodeClick(data) {-->
<!--        this.queryParams.deptId = data.id;-->
<!--        this.getList();-->
<!--      },-->
<!--    }-->
<!--  }-->
<!--</script>-->
<script>
  export default {
    data() {
      return {
        data: [{
          id: 1,
          label: '基本 1',
          children: [{
            id: 4,
            label: '普通 1-1',
          }]
        }, {
          id: 2,
          label: '基本 2',
          children: [{
            id: 5,
            label: '普通 2-1'
          }, {
            id: 6,
            label: '普通 2-2'
          }]
        }, {
          id: 3,
          label: '基本 3',
          children: [{
            id: 7,
            label: '普通 3-1'
          }, {
            id: 8,
            label: '普通 3-2'
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      };
    },
    created() {
      this.getTreeselect();
      const gradeId = this.$route.params.gradeId;
    },
    methods:{
      handleNodeClick(){
        this.$router.push({
          path: '/test/member',
          component: (resolve) => require(['@/views/test/member/index'], resolve),
        })
        }
      }
  };
</script>
