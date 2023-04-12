<template>
  <div>
    <el-container>
      <el-aside width="10%" class="" style="position: fixed;top: 250px">
        <el-row type="flex" justify="center">
          <el-col>
            <img style="width: 150px" class="pic-1" :src="loadImg(barInfo.barHead)">
            <p>{{ barInfo.barName }}</p>
            <div>
              吧主：{{ barInfo.barHost }}
            </div>
            <div>
              关注人数：{{ barInfo.userCountAll }}
            </div>
            <el-button @click="dialogFormVisible = true;" v-if="isRegard===true" type="primary"
                       style="margin-top: 10px">
              发帖
            </el-button>
            <el-button @click="regardBar" v-if="isRegard===false" style="margin-top: 10px">
              关注
            </el-button>
            <el-button @click="noRegardBar" type="danger" v-if="isRegard===true" style="margin-top: 10px">
              取消关注
            </el-button>
            <p v-if="isRegard===false">关注后可以发帖</p>
          </el-col>
        </el-row>
      </el-aside>
      <el-main>
        <div style="float: right;width: 90%">
          <div v-infinite-scroll="load" infinite-scroll-disabled="disabled" class="comm">
            <div v-for="invitation in invitationInfo">
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
                    <el-col :span=4>
                      <el-button type="primary" size="mini" @click="publishComment">发表</el-button>
                    </el-col>
                  </el-row>
                </el-form-item>

                <div v-for="comment in invitationComment">
                  <el-row>
                    <el-col :span=4>
                      <img style="width: 50px" :src="loadUserImg(comment.userHead)">
                      <p>{{ comment.userNickName }}</p>
                      <el-button @click="deleteComment(comment)" v-show="comment.userId==userId" size="small" type="danger">删除</el-button>
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
        </div>
      </el-main>
    </el-container>
    <!--    点击新增后会弹出来的一个对话框-->
    <el-dialog title="新增帖子" :visible.sync="dialogFormVisible">
      <el-form :model="form" status-icon :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
        <el-form-item label="主题" prop="topic">
          <el-input type="text" v-model="form.topic" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input type="textarea" v-model="form.content" autocomplete="off" maxlength="50"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="publish('form');dialogFormVisible=false">添加</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>

<script>
export default {
  data() {
    var validateTopic = (rule, value, callback) => {
      if (value === '' || value === undefined) {
        callback(new Error('请输入主题'));
      }
      callback();
    };
    var validateContent = (rule, value, callback) => {
      if (value === '' || value === undefined) {
        callback(new Error('请输入内容'));
      }
      callback();
    };
    return {
      userId:sessionStorage.getItem("userId"),
      barName: this.$route.query.barName,
      count: 1,//起始页数值为0
      loading: false,
      totalPages: '',//取后端返回内容的总页数
      pageNumber: "",
      barInfo: {
        id: '',
        barName: '',
        barHost: '',
        userCountAll: '',
        barHead: '',
        barIntroduction: '',
        createTime: ''
      }, //后端返回的数组
      //是否关注了本吧
      isRegard: '',
      isRegardInfo: {
        userId: sessionStorage.getItem("userId"),
        barId: ''
      },
      invitationInfo: [{
        id: '',
        topic: '',
        content: '',
        belongBarId: '',
        belongUserId: '',
        createTime: '',
        updateTime: '',
        likeCount: '',
        commentCount: ''
      }], //后端返回的数组
      params: {
        pageNumber: '',
        pageSize: 10, //每页查询条数
        barName: ''
      },
      dialogFormVisible: false,
      form: {
        topic: '',
        content: '',
        belongBarName: '',
        belongUserEmail: sessionStorage.getItem("userId"),
      },
      rules: {
        topic: [
          {validator: validateTopic, trigger: 'blur'}
        ],
        content: [
          {validator: validateContent, trigger: 'blur'}
        ]
      },
      formLabelWidth: '120px',
      dialogVisible: false,
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
        id:'',
        userId:'',
        userNickName: '',
        userHead: '',
        content: '',
        belongInvitation:''
      }]
    }
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
    deleteComment(comment){
      axios.post("/index/barView/deleteComment",comment).then(()=>{
        this.$message({
          type:'success',
          message:'删除成功'
        });
        this.getInvitationComment(comment.belongInvitation);
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
    },
    loadImg(path) {
      try {
        return require("@/assets/barHead/" + path)
      } catch (e) {
      }
    },
    loadUserImg(path) {
      try {
        return require("@/assets/userHead/" + path)
      } catch (e) {
      }
    },
    load() {
      //滑到底部时进行加载
      this.loading = true;
      setTimeout(() => {
        this.count += 1; //页数+1
        this.getMessage(); //调用接口，此时页数+1，查询下一页数据
      }, 2000);
    },
    getMessage() {
      this.params.barName = this.barName;
      axios.post("/index/barView/getBarInvitationPage", this.params).then((res) => {
        console.log(res);
        this.invitationInfo = this.invitationInfo.concat(res.data.data.records); //因为每次后端返回的都是数组，所以这边把数组拼接到一起
        //取总页数
        this.totalPages = res.data.data.total / 10;
        this.loading = false;
      })
    },
    cgetMessage() {
      this.params.barName = this.barName;
      axios.post("/index/barView/getBarInvitationPage", this.params).then((res) => {
        // console.log(res);
        this.invitationInfo = res.data.data.records;
        this.totalPages = res.data.data.total / 10;
        this.loading = false;
      })
    },
    regardBar() {
      axios.post("/index/barView/regardBar", this.isRegardInfo).then((res) => {
        this.isRegard = true;
        axios.post("/index/barView/increaseBarCount")
        this.barInfo.userCountAll += 1;
      })
    },
    noRegardBar() {
      axios.post("/index/barView/noRegardBar", this.isRegardInfo).then((res) => {
        this.isRegard = false;
        axios.post("/index/barView/decreaseBarCount")
        this.barInfo.userCountAll -= 1;
      })
    },
    publish() {
      axios.post("/index/barView/publish", this.form).then((res) => {
        //发帖成功后刷新页面
        this.cgetMessage();
        this.form.topic = '';
        this.form.content = '';
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
    //根据吧名请求相应的数据进行显示
    this.cgetMessage();
    this.params.pageNumber = this.count;
    this.form.belongBarName = this.barName;
    // this.form.belongUserEmail = se
    axios.get("/index/barView/getBarInfomation?barName=" + this.barName).then((res) => {
      // console.log(res);
      this.barInfo = res.data.data;
      this.commentInfo.belongBarId = res.data.data.id;
      // console.log(this.barInfo)
      this.isRegardInfo.barId = this.barInfo.id;
      // console.log(this.isRegardInfo);
      //判断该用户是否关注了进去的这个吧,大概因为请求是异步的，这个请求需要外面请求的数据，所以放里面
      axios.post("/index/barView/checkIsRegard", this.isRegardInfo).then((res) => {
        // console.log(res);
        if (res.data.data === 1) {
          this.isRegard = true
        } else {
          this.isRegard = false;
        }
      })
    })
    //
  }
}
</script>