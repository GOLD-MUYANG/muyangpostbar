<template>
  <div>
    <div>

      <!--    上面是格外需要关注的大吧-->
      <el-row type="flex" justify="center">
        <el-col :span=4 v-for="o in userMostInterestBar">
          <el-card :body-style="{ padding: '0px' }">
            <el-row type="flex" justify="center">
              <el-col :span=16>
                <img style="width: 150px;height: 150px;" :src=loadImg(o.barHead)
                     class="image">
              </el-col>
            </el-row>
            <div style="padding: 14px;">
              <span>{{o.barName}}</span>
              <p>{{o.barIntroduction}}</p>
              <p>关注人数：{{o.userCountAll}}</p>
                <router-link :to="{path:'/index/barView?barName='+o.barName}">
                  <p style="font-size: 15px;color: #1989fa">进去看看</p>
                </router-link>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <!--    下面是不太常关注的小吧-->
      <el-row>
        <el-col :span=8 v-for="userI in userInterestBar">
          <div>
            <router-link :to="{path:'/index/barView?barName='+userI.barName}">
              <div class="box">
                <img style="width: 100px;height: 100px;"
                     :src=loadImg(userI.barHead)>
                <div class="box-content" style="cursor: pointer;">
                  <h3 class="title"></h3>
                  <p class="post">{{userI.barName}}</p>
                  <p class="post">{{userI.barIntroduction}}</p>
                </div>
              </div>
            </router-link>

          </div>
        </el-col>
      </el-row>

    </div>
  </div>
</template>

<style scoped>
.box {
  font-family: 'Kanit', sans-serif;
  text-align: center;
  border: 10px solid #fff;
  box-shadow: 1px 1px 2px #e6e6e6;
  overflow: hidden;
  position: relative;
}

.box:hover {
  box-shadow: 13px 13px 15px rgba(0, 0, 0, 0.3);
}

.box:before {
  content: "";
  background: linear-gradient(to left top, #ffffff, #ffffff);
  height: 100%;
  width: 100%;
  opacity: 0;
  position: absolute;
  left: 0;
  top: 0;
  transition: all 0.4s linear;
}

.box:hover:before {
  opacity: 1;
}

.box img {
  width: 100%;
  height: auto;
  transition: all 0.4s linear;
}

.box:hover img {
  opacity: 0;
  transform: scale(3);
}

.box .box-content {
  color: #000000;
  width: 100%;
  transform: translateY(-50%) scale(0);
  position: absolute;
  top: 50%;
  left: 0;
  transition: all 0.4s linear;
}

.box:hover .box-content {
  transform: translateY(-50%) scale(1);
}

.box .title {
  font-size: 25px;
  font-weight: 600;
  letter-spacing: 1px;
  text-transform: uppercase;
  margin: 0;
}

.box .post {
  font-size: 16px;
  text-transform: capitalize;
}

.box .icon {
  padding: 0;
  margin: 0;
  list-style: none;
  position: absolute;
  right: 8px;
  bottom: 10px;
}

.box .icon li {
  display: inline-block;
  margin: 0 1px;
  opacity: 0;
  transform: scale(1.3);
  transition: all 0.3s ease 0.4s;
}

.box:hover .icon li {
  opacity: 1;
  transform: scale(1);
}

.box:hover .icon li:nth-child(2) {
  transition: all 0.3s ease 0.2s;
}

.box .icon li a {
  color: #00ffee;
  background: #fff;
  font-size: 16px;
  line-height: 33px;
  height: 33px;
  width: 33px;
  border-radius: 50%;
  display: block;
  transition: all 0.5s ease;
}

.box .icon li a:hover {
  color: #fff;
  background-color: #ee0000;
  box-shadow: 0 0 5px #000;
}

@media only screen and (max-width: 990px) {
  .box {
    margin: 0 0 30px;
  }
}
</style>

<script>
export default {
  data() {
    return {
      src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      userInterestBar: [{
        id: '',
        barName: '',
        barHost: '',
        userCountAll: '',
        barHead: '',
        barUrl: '',
        barIntroduction: ''
      }],
      userMostInterestBar: [{
        id: '',
        barName: '',
        barHost: '',
        userCountAll: '',
        barHead: '',
        barUrl: '',
        barIntroduction: ''
      }],
      userInfo:{
        userId:sessionStorage.getItem("userId")
      }
    }
  },
  methods: {
    loadImg(path){
      try {
        return require("@/assets/barHead/"+path);
      }catch (e){

      }
    }
  },
  created() {
    //先请求最常进入的几个吧
    axios.post("/index/bar/getUserMostInterestBar",this.userInfo).then((res) => {
      this.userMostInterestBar = res.data.data;
    })
    //然后在把用户关注的所有吧请求出来
    axios.post("/index/bar/getUserInterestBar",this.userInfo).then((res) => {
      this.userInterestBar = res.data.data;
    })
  }

}
</script>

