<template>
  <div>
    <div  v-infinite-scroll="load" infinite-scroll-disabled="disabled"  class="comm">
      <div v-for="invitation in hotInvitationInfo">
        <div @click="dialogVisible = true;setBelongComment(invitation);getInvitationComment(invitation.id)"
             style="cursor: pointer">
          <el-row type="flex" justify="center">
            <el-col :span=20>
              <p>{{ invitation.topic }}<span style="margin-left: 100px">热度：{{ invitation.likeCount }}</span></p>
            </el-col>
          </el-row>
          <el-row type="flex" justify="center" style="margin-bottom: 80px">
            <el-col :span=20>
              <p>{{ invitation.content }}</p>
            </el-col>
          </el-row>
        </div>
        <el-divider></el-divider>
      </div>
      <el-dialog
          title="评论"
          :visible.sync="dialogVisible"
          width="80%"
          :before-close="handleClose">
        <el-form :model="form">
          <el-form-item label="输入评论" :label-width="formLabelWidth">
            <el-row>
              <el-col :span=20>
                <el-input v-model="commentInfo.content" autocomplete="off" maxlength="100"
                          minlength="1"></el-input>
              </el-col>
              <el-col :span=2>
                <el-button type="primary" size="mini" @click="publishComment">发表</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <div v-for="comment in invitationComment">
            <el-row>
              <el-col :span=4>
                <img style="width: 50px" :src="loadUserImg(comment.userHead)">
                <p>{{ comment.userNickName }}</p>
              </el-col>
              <el-col :span=20 :pull=10 style="margin-top: 40px">
                {{ comment.content }}
              </el-col>
            </el-row>
            <el-divider></el-divider>
          </div>
        </el-form>
        <span slot="footer" class="dialog-footer">
  </span>
      </el-dialog>
      <p v-if="loading" style="margin-top:10px;" class="loading">
        <span></span>
      </p>
      <p v-if="noMore" style="margin-top:10px;font-size:13px;color:#ccc">没有更多了</p>
    </div>
    <el-backtop target=".comm" :bottom="100">
      <div
          style="{
        height: 100%;
        width: 100%;
        background-color: #f2f5f6;
        box-shadow: 0 0 6px rgba(0,0,0, .12);
        text-align: center;
        line-height: 40px;
        color: #1989fa;
      }"
      >
        UP
      </div>
    </el-backtop>
  </div>

</template>

<script>
export default {
  data() {
    return {
      count: 1,//起始页数值为0
      loading: false,
      totalPages: '',//取后端返回内容的总页数
      pageNumber:"",
      hotInvitationInfo: [{
        id:'',
        topic:'',
        content:'',
        belongBarId:'',
        belongUserId:'',
        createTime:'',
        updateTime:'',
        likeCount:'',
        commentCount:''
      }], //后端返回的数组
      dialogVisible : false,
      //这是发表评论时用的
      commentInfo: {
        id: '',
        belongBarId: '',
        belongUserId: sessionStorage.getItem("userId"),
        belongInvitation: '',
        content: ''
      },
      //这是获取该帖子下面的评论所需要的，是被res设置的
      invitationComment: [{
        userNickName: '',
        userHead: '',
        content: ''
      }],
      form: {
        topic: '',
        content: '',
        belongBarName: '',
        belongUserEmail: sessionStorage.getItem("userId"),
      },
      formLabelWidth:100
    };
  },
  computed: {
    noMore() {
      //当起始页数大于总页数时停止加载
      return this.count >= this.totalPages;
    },
    disabled() {
      return this.loading || this.noMore;
    }
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
    },
    load() {
      //滑到底部时进行加载
      this.loading = true;
      setTimeout(() => {
        this.count += 1; //页数+1
        this.getMessage(); //调用接口，此时页数+1，查询下一页数据
      }, 2000);
    },
    loadUserImg(path) {
      try {
        return require("@/assets/userHead/" + path)
      } catch (e) {
      }
    },
    getMessage() {
      let params = {
        pageNumber: this.count,
        pageSize: 10 //每页查询条数
      };
      axios.post("/index/hotInvitation/getHotInvitationPage", params).then((res) => {
        console.log(res);
        this.hotInvitationInfo = this.hotInvitationInfo.concat(res.data.data.records); //因为每次后端返回的都是数组，所以这边把数组拼接到一起
        //取总页数
        this.totalPages = res.data.data.total / 10;
        this.loading = false;
      })
    },
    cgetMessage() {
      let params = {
        pageNumber: this.count,
        pageSize: 10 //每页查询条数
      };
      axios.post("/index/hotInvitation/getHotInvitationPage", params).then((res) => {
        // console.log(res);
        this.hotInvitationInfo = res.data.data.records;
        this.totalPages = res.data.data.total / 10;
        this.loading = false;
      })
    },
    publishComment() {
      //需要拿到的是：帖子的id 用户id,吧id，
      axios.post("/index/barView/publishComment", this.commentInfo).then((res) => {
        this.commentInfo.content = '';
        this.getInvitationComment(this.commentInfo.belongInvitation);
      })
    },
    setBelongComment(invitation) {
      this.commentInfo.belongInvitation = invitation.id;
    },
    getInvitationComment(invitationId) {
      //查询评论信息，需要帖子的id,拿到用户id，评论内容，根据用户id再去查用户信息
      axios.post("/index/barView/getInvitationComment", {invitationId: invitationId}).then((res) => {
        console.log(res);
        this.invitationComment = res.data.data;
      })
    }
  },
  created() {
    this.cgetMessage();
  },
};
</script>

<style scoped>
.box {
  width: 100%;
  margin:  0 auto;
}
.list {
  padding: 0;
  font-size: 14px;
}
.loading span {
  display: inline-block;
  width: 20px;
  height: 20px;
  border: 2px solid #409eff;
  border-left: transparent;
  animation: zhuan 0.5s linear infinite;
  border-radius: 50%;
}
@keyframes zhuan {
  0% {
    transform: rotate(0);
  }
  100% {
    transform: rotate(360deg);
  }
}

.comm{
  height: 100vh;
  overflow-x: hidden;
}
</style>
