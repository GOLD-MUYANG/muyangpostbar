<template>
  <div>
    <el-row type="flex" justify="center">
      <el-col :span=8 style="margin-top: 150px">
        <p style="font-size: 30px;text-align: center" >用户注册</p>
        <el-col :span=20 :pull=2>
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="200px"
                   class="demo-ruleForm">
            <el-form-item label="邮箱" prop="email">
              <el-input type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="真实姓名" prop="realName">
              <el-input type="text" v-model="ruleForm.realName" autocomplete="off"></el-input>
            </el-form-item>

            <el-col :push=5  style="margin-top: 15px">
                <el-button type="primary" @click="registForm('ruleForm')">点击进行注册</el-button>
                <el-button type="success" @click="backToLogin()">回到登录页面</el-button>
            </el-col>
          </el-form>
        </el-col>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  name: "regist",
  data() {
    // 校验规则
    var validateEmail = (rule, value, callback) => {
      var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
      if (value === ''||value===undefined) {
        callback(new Error('请输入邮箱'));
      }else if (!reg.test(value)){
        callback(new Error('请检查格式'));
      }
      callback();
    };
    var validatePass = (rule, value, callback) => {
      if (value === ''||value===undefined) {
        callback(new Error('请输入密码'));
      }
      callback();
    };
    var validateName = (rule, value, callback) => {
      if (value === ''||value===undefined) {
        callback(new Error('请输入真实姓名'));
      }
      callback();
    };
    return {
      // 数据
      ruleForm: {
        email: '',
        password: '',
        realName:''
      },
      // 规则
      rules: {
        email: [
          {validator: validateEmail, trigger: 'blur'}
        ],
        password: [
          {validator: validatePass, trigger: 'blur'}
        ],
        realName: [
          {validator: validateName, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    registForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            axios.post("/login/add-user-info", this.ruleForm).then((res) => {
              console.log(this.$data.ruleForm.real_name)
              if (res.data.code == 0) {
                this.$message({
                  message:'注册成功!',
                  type:'success'
                })
                this.$router.push("/");
              } else {
                this.$message({
                  message:'注册失败，该用户已经存在',
                  type:'error'
                })
                return false;
              }
            })
        }
      });
    },

    backToLogin() {
      this.$router.push("/login")
    }

  }
}
</script>