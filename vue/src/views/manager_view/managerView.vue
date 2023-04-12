<template>
  <div>
    <el-container style="height: 100vh; border: 1px solid #eee">
      <el-aside width="200px" style="border-right: #afafaf 1px solid">
        <el-menu :router=true background-color="#ffffff">
          <!--          请求到数据库对应的权限url，放在这里遍历-->
          <el-menu-item v-for="manager_info in managerInfo.roles">
            <router-link :to="manager_info.url">{{ manager_info.actionName }}</router-link>
          </el-menu-item>
        </el-menu>
        <el-button @click="logOut" style="position: absolute ;bottom: 40px;left: 60px">
          退出登录
        </el-button>
      </el-aside>
      <el-container>
        <el-header style="text-align: right; font-size: 15px">
          <span>{{ managerInfo.name }}</span>
        </el-header>
        <el-main>
          <router-view name="fun"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style>
html, body {
  padding: 0;
  margin: 0;
}


a{
  text-decoration: none;
  color: black;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>

<script>
export default {

  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      tableData: Array(10).fill(item),
      managerInfo: {
        name: '',
        roles: ''
      }
    }
  },
  methods:{
    logOut(){
      sessionStorage.removeItem("email");
      sessionStorage.removeItem("password");
      sessionStorage.removeItem("managerLogging");
      this.$router.push("/")
    }
  },
  created() {
    //进入到页面后首先把该管理员的信息取到，拿到该管理员的权限，遍历到页面上
    axios.post("/manage/gotManagerInfo", {
      email: sessionStorage.getItem("email"),
      password: sessionStorage.getItem("password")
    }).then((res) => {
      // console.log(res);
      this.managerInfo.name = res.data.data.name;
      this.managerInfo.roles = res.data.data.roles;
    })
  },


};
</script>