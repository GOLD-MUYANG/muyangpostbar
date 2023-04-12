<template>
  <div class="block">
    <span class="demonstration big-font">公告显示</span>
    <el-carousel height="400px">
      <el-carousel-item v-for="item in ann" size="medium">
        <el-row type="flex" justify="center" style="margin-top: 50px">
          <el-col :span=10>
            主题：{{item.topic}}
          </el-col>
          <el-col :span=10>
            发布时间：{{item.createTime}}
          </el-col>
        </el-row>
        <el-row type="flex" justify="center" style="margin-top: 50px">
          <el-col :span=16>
            {{item.content}}
          </el-col>

        </el-row>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<style>
.el-carousel__item h3 {
  color: #ffffff;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.big-font{
  font-size: 25px;
}
</style>

<script>
export default {
  data(){
    return {
      ann:[
        {
          topic:'',
          content:'',
          createTime:''
        }
      ]
    }
  },
  created() {
    axios.get("/index/ann/getAnn").then((res)=>{
      this.ann = res.data.data;
    })
  }
}
</script>