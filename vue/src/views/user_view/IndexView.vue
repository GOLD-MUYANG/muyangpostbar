<template>
  <div>
    <el-container>
      <el-header style="margin-bottom: 30px">
        <el-menu :router=true class="el-menu-demo" mode="horizontal"
                 @select="handleSelect" style="overflow: hidden">
          <!--          请求到数据库对应的权限url，放在这里遍历-->
          <el-menu-item v-for="index_action in indexAction">
            <router-link :to="index_action.url">{{ index_action.name }}</router-link>
          </el-menu-item>
          <el-menu-item>
            <el-input type="text"  v-model="searchBar" placeholder="输入你想进入的吧"></el-input>
            <router-link :to="{path:'/index/barView?barName='+searchBar}" @click.native="flush">
            <el-button type="primary" size="mini">
              搜索
            </el-button>
            </router-link>
          </el-menu-item>

          <el-col :span=2 :push=12>
            <div class="sub-title" v-if="userLogging!=='userLogging'">请先登录</div>
            <div class="sub-title" v-if="userLogging==='userLogging'">{{userInfo.nickName}}</div>
            <div class="block">
              <el-avatar  :size="40" :src=loadImg(userInfo.head)></el-avatar >
            </div>
          </el-col>
          <el-col :span=2 :push=12>
            <el-dropdown style="top: 40px;right: 80px">
              <span class="el-dropdown-link"><i class="el-icon-arrow-down el-icon--right"></i></span>
              <el-dropdown-menu slot="dropdown">
<!--                点击登录后直接跳转登录页面，登录后不显示此按钮-->
                <el-dropdown-item v-show="userLogging!=='userLogging'">
                  <router-link to="/">
                    登录
                  </router-link>
                </el-dropdown-item>
                <el-dropdown-item>
                  <router-link to="userSelfInfo">个人信息</router-link>
                </el-dropdown-item>
                <el-dropdown-item v-show="userLogging==='userLogging'">
                  <a @click="logOut" >退出登录</a>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
          <el-col>
          </el-col>

        </el-menu>
      </el-header>
      <el-container>
        <el-container>
          <el-main>
            <router-view name="indexView"></router-view>
          </el-main>
          <el-footer>
            <div>
              <span></span>
              <span>法律声明</span>
              <el-divider direction="vertical"></el-divider>
              <span>友情链接</span>
              <el-divider direction="vertical"></el-divider>
              <span @click="drawer = true" style="cursor: pointer">联系我</span>
              <br/>
              <br/>
              <span>版权所有 MUYANG</span>
              <el-drawer
                  title="联系我"
                  :visible.sync="drawer">
                <p class='info'>
                  <i class="el-icon-phone"></i>电话：15265746655<br/>
                  <i class="el-icon-message"></i>邮箱：2156174430@qq.com<br/>
                </p>
              </el-drawer>
            </div>
          </el-footer>
        </el-container>
      </el-container>
    </el-container>

  </div>
</template>
<style>

a{
  font-size: 15px;
  text-decoration: none;
  color: black;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
}


</style>

<script>
export default {
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1',

      userinfo:{
        email:'',
        password:'',
        realName:'',
        nick_name:'',
        head:'',
        createTime:'',
        updateTime:''
      },
      indexAction:[{
        id:'',
        name:'',
        url:''
      }],
      drawer: false,
      direction: 'btt',
      userLogging:sessionStorage.getItem("userLogging"),
      userInfo:{
        email:sessionStorage.getItem("userEmail"),
        password:sessionStorage.getItem("userPassword"),
        head:'',
        nickName: '',
        createTime:'',
        updateTime:''
      },
      searchBar:'',
    };
  },
  methods: {
    flush(){
      this.$router.go(0);
    },
    logOut() {
      console.log("ok");
      sessionStorage.removeItem("userEmail");
      sessionStorage.removeItem("userPassword");
      sessionStorage.removeItem("userLogging");
      this.$router.push("/")
    },

    handleSelect(key, keyPath) {
      // console.log(key, keyPath);
    },
    handleClick() {
      alert('button click');
    },
    handleClose (done) {
      done()
    },
    loadImg(path){
      try {
          return require("@/assets/userHead/"+path);
      }catch (e){
      }
    }
  },
  created() {
    //页面创建后就请求用户信息，设置头像，以及渲染页面的功能
    axios.get("/index/getIndexAction").then((res)=>{
      // console.log(res);
      this.indexAction = res.data.data;
    })
    // console.log(this.userLogging);
    axios.post("/index/getUserInfo",this.userInfo).then((res)=>{
      this.userInfo = res.data.data;
    })
  }
}
</script>