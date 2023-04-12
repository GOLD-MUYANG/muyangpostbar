<template>
  <div>
    <el-row type="flex" justify="center">
      <el-col :span=8 style="margin-top: 150px">
        <span>{{this.$route.query.msg}}</span>
        <p style="font-size: 30px;text-align: center">登录</p>
        <el-col :span=20 :pull=2>
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="200px"
                   class="demo-ruleForm">
            <el-form-item label="邮箱" prop="email">
              <el-input type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
            </el-form-item>

            <el-col :offset=4 style="margin-top: 10px">
              <el-radio v-model="radio" label="1">用户</el-radio>
              <el-radio v-model="radio" label="2">管理员</el-radio>
            </el-col>

            <el-col :offset=4 style="margin-top: 20px">
              <el-checkbox v-model="remember" label="one">记住登录信息</el-checkbox>
            </el-col>

            <el-col :pull=1 style="margin-top: 15px">
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                <el-button type="success" @click="registUser()" v-if="radio!=2">注册</el-button>
              </el-form-item>
            </el-col>
          </el-form>
        </el-col>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import Cookies from 'js-cookie'

export default {

  name: "login",
  data() {
    // 校验规则
    var validateEmail = (rule, value, callback) => {
      if (value === '' || value === undefined) {
        callback(new Error('请输入邮箱'));
      }
      callback();
    };
    var validatePass = (rule, value, callback) => {
      if (value === '' || value === undefined) {
        callback(new Error('请输入密码'));
      }
      callback();
    };
    return {
      // 数据
      ruleForm: {
        email: Cookies.get("email"),
        password: Cookies.get("password"),
      },
      // 规则
      rules: {
        email: [
          {validator: validateEmail, trigger: 'blur'}
        ],
        password: [
          {validator: validatePass, trigger: 'blur'}
        ]
      },
      radio: '1',
      remember: true
    };
  },
  methods: {
    logv() {
      console.log(this.$data.radio);
      console.log(this.$data.ruleForm.email);
      console.log(this.$data.ruleForm.password);
      console.log(this.$data.remember)
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 如果选择了用户登录
          if (this.radio == "1") {
            axios.post("/login/check-user-info", this.ruleForm).then((res) => {
              //如果在数据库中查询到用户输入的信息，准备登录
              sessionStorage.setItem("userId",res.data.data.id);
              if (res.data.code == 0) {
                console.log(res);
                //如果选择了记住用户信息，设置cookie
                if (this.$data.remember === true) {
                  Cookies.set("email", this.$data.ruleForm.email, {expires:100});
                  Cookies.set("password", this.$data.ruleForm.password,{expires:100});
                } else {
                  Cookies.remove("email");
                  Cookies.remove("password");
                }
                if (res.data.data.state!=0){
                  this.$message({
                    message:'登录成功!!',
                    type:'success'
                  })
                  //登陆成功后把用户信息存到session里，后面
                  //可以拿来用户信息查询其他东西
                  sessionStorage.setItem("userEmail", this.ruleForm.email);
                  sessionStorage.setItem("userPassword", this.ruleForm.password);
                  sessionStorage.setItem("userLogging","userLogging");
                  // 跳转页面
                  this.$router.push("/index");
                }
                else {
                  this.$message({
                    message:'登录失败,该用户被禁用!!',
                    type:'error'
                  })
                }
              } else {
                this.$message({
                  message:'登录失败,该用户不存在!!',
                  type:'error'
                })
                return false;
              }
            })
          }
          // 没有选择用户登录,跳转管理员界面
          else {
            axios.post("/login/check-manager-info", this.ruleForm).then((res) => {
              console.log(res)
              if (res.data.code == 0) {
                //如果选择了记住用户信息，设置cookie
                if (this.$data.remember === true) {
                  Cookies.set("email", this.$data.ruleForm.email, {expires:100});
                  Cookies.set("password", this.$data.ruleForm.password, {expires:100});
                } else {
                  Cookies.remove("email");
                  Cookies.remove("password");
                }
                this.$message({
                  message:'登录成功!!',
                  type:'success'
                })
                //登陆成功后把用户信息存到session里，后面
                //可以拿来用户信息查询其他东西
                sessionStorage.setItem("email", this.ruleForm.email);
                sessionStorage.setItem("password", this.ruleForm.password);
                sessionStorage.setItem("managerLogging","managerLogging");
                sessionStorage.setItem("roleId",res.data.data.id);
                this.$router.push("/manage");
              } else {
                this.$message({
                  message:'登录失败,请检查邮箱或密码是否正确!!',
                  type:'error'
                })
                return false;
              }
            })
          }
        }
      });
    },
    registUser() {
      this.$router.push("/regist");
    }
  },
  created() {
    this.$data.ruleForm.email = Cookies.get("email"),
    this.$data.ruleForm.password = Cookies.get("password")
  }
}
</script>