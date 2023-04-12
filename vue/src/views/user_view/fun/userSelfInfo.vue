<template>
  <div style="justify-content: center;display: flex;">
    <div style="width: 800px">
      <el-container>
        <el-main>
          <div class="demo-image__placeholder">
            <div class="block">
              <span class="demonstration"></span>
              <el-row justify="center" type="flex">
                <el-col :span=10>
                  <img :src=loadImg(this.userInfo.head) style="width: 200px;height: 200px;border-radius: 100px">
                </el-col>
                <el-col style="margin-top: 130px" :span=3 :pull=1>
                  <el-tag style="cursor: pointer" @click="dialogFormVisible = true">点击上传新头像</el-tag>
                </el-col>
              </el-row>

              <!--              修改头像的部分-->
              <el-dialog title="修改头像" :visible.sync="dialogFormVisible">
                <el-upload
                    action="#"
                    list-type="picture-card"
                    :auto-upload="false"
                    ref="upload"
                    :limit="1"
                    :file-list="fileList"
                    :on-change="handleChange"
                >
                  <i slot="default" class="el-icon-plus"></i>
                  <div slot="file" slot-scope="{ file }">
                    <!--显示上传图片 -->
                    <img class="el-upload-list__item-thumbnail img" :src="file.url" alt=""/>
                    <span class="el-upload-list__item-actions">
                    <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                      <i class="el-icon-delete"></i>
                    </span>
                  </span>
                  </div>
                </el-upload>
                <el-button class="btn" @click="upload"><i class="el-icon-paperclip"></i>上传图片</el-button>
              </el-dialog>
              <el-divider></el-divider>

              <!--              个人信息显示-->
              <el-row type="flex" justify="center">
                <el-col :span=22>
                  <el-descriptions :column=2 size="medium">
                    <el-descriptions-item label="昵称">
                      {{ userInfo.nickName }}
                      <el-tag @click="dialogNickNameVisible=true" style="cursor: pointer">修改</el-tag>
                      <el-dialog title="修改昵称" :visible.sync="dialogNickNameVisible">
                        <el-input v-model="chNickName" placeholder="请输入要修改的内容"></el-input>
                        <el-button class="btn" @click="changeNickName();dialogNickNameVisible=false"><i
                            class="el-icon-paperclip"></i>修改昵称
                        </el-button>
                      </el-dialog>
                    </el-descriptions-item>

                    <el-descriptions-item label="邮箱">{{ userInfo.email }}</el-descriptions-item>
                    <el-descriptions-item label="密码">
                      {{ userInfo.password }}
                      <el-tag @click="dialogPasswordVisible=true" style="cursor: pointer">修改</el-tag>
                      <el-dialog title="修改密码" :visible.sync="dialogPasswordVisible">
                        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                                 class="demo-ruleForm">
                          <el-form-item label="密码" prop="chPassword">
                            <el-input type="password" v-model="ruleForm.chPassword" autocomplete="off"></el-input>
                          </el-form-item>
                          <el-form-item label="确认密码" prop="chCheckPassword">
                            <el-input type="password" v-model="ruleForm.chCheckPassword" autocomplete="off"></el-input>
                          </el-form-item>
                          <el-form-item>
                            <el-button type="primary" @click="submitForm('ruleForm');dialogPasswordVisible=false">提交</el-button>
                          </el-form-item>
                        </el-form>
                      </el-dialog>
                    </el-descriptions-item>
                    <el-descriptions-item label="创建用户时间">{{ userInfo.createTime }}</el-descriptions-item>
                  </el-descriptions>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-main>
      </el-container>
    </div>
  </div>
</template>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
        callback();
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.chPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        chPassword: '',
        chCheckPassword:''
      },
      rules: {
        chPassword: [
          {validator: validatePass, trigger: 'blur'}
        ],
        chCheckPassword: [
          {validator: validatePass2, trigger: 'blur'}
        ]
      },
      userInfo: {
        email: sessionStorage.getItem("userEmail"),
        password: sessionStorage.getItem("userPassword"),
        realName: '',
        nickName: '',
        head: '',
        createTime: '',
        updateTime: ''
      },
      dialogFormVisible: false,
      dialogNickNameVisible: false,
      dialogPasswordVisible: false,
      dialogVisible: false,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formLabelWidth: '120px',
      imageUrl: '',
      dialogImageUrl: '',
      disabled: false,
      fileList: [],
      headers: {
        'Content-Type': 'multipart/form-data'
      },
      chNickName: '',
    }
  },
  methods: {
    lo() {
      alert("aa")
    },
    loadImg(path) {
      try {
        return require("@/assets/userHead/" + path)
      }catch (e){}
    },
    handleRemove(file) {
      //使用indexOf把点击的图片的索引位置找出来删掉
      let arr = this.$refs.upload.uploadFiles;
      let index = arr.indexOf(file);
      console.log(index);
      arr.splice(index, 1)
    },

    handleChange(file, fileList) { //文件数量改变
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      this.fileList = fileList;
      return isJPG && isLt2M;
    },

    upload: function () {
      var param = new FormData();
      this.fileList.forEach(
          (val, index) => {
            param.append("file", val.raw);
          }
      );
      axios.post("/index/userSelfInfo/uploadHead", param).then((res) => {
        console.log(res)
        this.$message({
          message: "修改成功",
          type: 'success',
          duration: 1000
        });
        this.userInfo.head = res.data.data;
        axios.post("/index/userSelfInfo/chanegUserHead", this.userInfo).then((res) => {
        })
      }).catch((err)=>{
      });
    },

    changeNickName() {
      this.userInfo.nickName = this.chNickName;
      console.log(this.userInfo);
      axios.post("/index/userSelfInfo/changeNickName", this.userInfo).then((res) => {
        this.$message({
          message: "修改成功",
          type: 'success'
        })
      })
    },

    //修改密码
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.userInfo.password = this.ruleForm.chPassword;
          axios.post("/index/userSelfInfo/changePassword",this.userInfo).then(()=>{
            this.$message({
              message: "修改成功，请重新登录",
              type: 'success'
            })
            this.$router.push("/")
            sessionStorage.removeItem("userPassword");
            sessionStorage.removeItem("userEmail");
            sessionStorage.removeItem("userLogging")
          })
        } else {
          this.$message({
            type:'error',
            message:'修改失败，请按提示操作'
          })
          return false;
        }
      });
    }
  },
  created() {
    axios.post("/index/userSelfInfo/getUserInfo", this.userInfo).then((res) => {
      this.userInfo = res.data.data;
      // console.log(res)
    })
  }
}
</script>
