<template>
  <div>
    <!--  模糊查询-->
    <el-row justify="end" type="flex" style="margin-left: 100px">
      <el-col :span=5 :push=2>
        <el-input v-model="topicInput" placeholder="主题模糊"></el-input>
      </el-col>
      <el-col :span=5 style="margin-left: 100px" :push=1>
        <el-input v-model="managerInput" placeholder="发布人模糊"></el-input>
      </el-col>
      <el-col :span=8>
        <div class="block">
          <el-date-picker
              v-model="dataValue"
              align="right"
              type="date"
              placeholder="选择日期"
              :picker-options="pickerOptions"
              value-format="yyyy-MM-dd">
          </el-date-picker>
        </div>
      </el-col>
      <el-col :span=3 :pull=2>
        <el-button type="primary" @click="search(topicInput,managerInput,dataValue)">点击查找</el-button>
      </el-col>
      <el-col :span=2 :pull=3>
        <el-button type="success" @click="dialogFormVisible = true">新增</el-button>
      </el-col>
      <!--    点击新增后会弹出来的一个对话框-->
      <el-dialog title="新增公告" :visible.sync="dialogFormVisible">
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
          <el-button type="primary" @click="saveAnn('form')">添加</el-button>
        </div>
      </el-dialog>
    </el-row>

    <!--  公告展示-->
    <div v-for="(item,index) in annData" style="margin-top: 50px">
      <el-descriptions>
        <el-descriptions-item label="主题">{{ item.topic }}</el-descriptions-item>
        <el-descriptions-item label="发布人">{{ item.createManagerId }}</el-descriptions-item>
        <el-descriptions-item label="发布时间">{{ item.createTime }}</el-descriptions-item>
        <el-descriptions-item label="内容">{{ item.content }}
        </el-descriptions-item>
      </el-descriptions>

      <el-row>
        <el-col :span=4 :push=20>
          <el-button type="success" @click="dialogchangeVisible = true;setChange(item)">修改</el-button>
          <el-button type="danger" @click="deleteAnn(item)">删除</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
    </div>
    <!--    点击新增后会弹出来的一个对话框-->
    <el-dialog title="修改公告" :visible.sync="dialogchangeVisible">
      <el-form :model=change status-icon label-width="100px" class="demo-ruleForm">
        <el-form-item label="主题">
          <el-input type="text" v-model="change.topic" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input type="textarea" v-model="change.content" autocomplete="off" maxlength="50"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogchangeVisible = false">取消</el-button>
        <el-button type="primary" @click="changeAnn(change);dialogchangeVisible = false">修改</el-button>
      </div>
    </el-dialog>

    <!--    分页-->
    <!--        total是页码的数量，从数据库拿出来设置给它-->
    <!--    page-size只是用来计算一共有多少页的，不是显示的效果-->
    <el-pagination
        class="paginationClass"
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size="3"
        layout="prev, pager, next"
        :total=total>
    </el-pagination>
    <!--    分页结束-->
  </div>
</template>

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
        callback(new Error('请输入该公告的内容'));
      }
      callback();
    };
    return {
      dialogchangeVisible: false,
      annData: [
        {
          id: '',
          topic: '',
          createManagerId: '',
          content: '',
          createTime: ''
        }],
      total: 0,
      //显示的默认初始页数
      currentPage: 1,
      topicInput: '',
      managerInput: '',
      dataValue: '',
      //模糊搜索的日期搜索
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }, {
          text: '清空',
          onClick(picker) {
            picker.$emit('pick', '');
          }
        }]
      },
      value1: '',
      value2: '',
      dialogFormVisible: false,
      form: {
        createManagerId: sessionStorage.getItem("roleId"),
        topic: '',
        content: ''
      },
      //用来修改公告，因为用了el-dialog,遍历的数据不能直接拿来用
      change: {
        id: '',
        topic: '',
        createManagerId: '',
        content: '',
        createTime: ''
      },
      rules: {
        topic: [
          {validator: validateTopic, trigger: 'blur'}
        ],
        content: [
          {validator: validateContent, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    setChange(item) {
      this.change.id = item.id;
      this.change.topic = item.topic;
      this.change.content = item.content;
    },
    changeAnn(change) {
      axios.post("/manage/ann/changeAnn", change).then((res) => {
        this.$message({
          message: '修改成功',
          type: 'success'
        }),
            //修改完毕后刷新数据
            axios.post("/manage/ann/getAnn/" + this.currentPage, {
              topicInput: this.topicInput,
              managerInput: this.managerInput,
              dataValue: this.dataValue
            }).then((res) => {
              this.$data.annData = res.data.data.records;
            });
        axios.post("/manage/ann/getAnnSearchCount", {
          topicInput: this.topicInput,
          managerInput: this.managerInput,
          dataValue: this.dataValue
        }).then((res) => {
          // console.log(res);
          this.total = res.data.data;
        });
      });

    },
    lo(item) {
      console.log(item);
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      // 点击分页页码后，进行需要查的页码查询
      // console.log(`当前页: ${val}`);
      // console.log("/manage/gotUser/"+`${val}`)
      axios.post("/manage/ann/getAnn/" + `${val}`, {
        topicInput: this.topicInput,
        managerInput: this.managerInput,
        dataValue: this.dataValue
      }).then((res) => {
        this.$data.annData = res.data.data.records;
      });
      axios.post("/manage/ann/getAnnSearchCount", {
        topicInput: this.topicInput,
        managerInput: this.managerInput,
        dataValue: this.dataValue
      }).then((res) => {
        console.log(res);
        this.total = res.data.data;
      });
    },
    //刚搜索只搜第一页的内容就行，后面分页的时候再加上
    search() {
      axios.post("/manage/ann/fuzzySearch", {
        topicInput: this.topicInput,
        managerInput: this.managerInput,
        dataValue: this.dataValue
      }).then((res) => {
        console.log(res);
        this.annData = res.data.data.records;
      });
      axios.post("/manage/ann/getAnnSearchCount", {
        topicInput: this.topicInput,
        managerInput: this.managerInput,
        dataValue: this.dataValue
      }).then((res) => {
        console.log(res);
        this.total = res.data.data;
      });
    },
    saveAnn(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post("/manage/ann/saveAnn", this.form).then(() => {
            // console.log(res);
            this.$message({
              message: '添加成功',
              type: 'success'
            });
            //添加完了以后要把所有输入的信息清除,要在请求完成后
            // 再来改变新增的值，因为请求是异步的，放在外面会被直接覆盖
            this.form.content = '';
            this.form.topic = '';
            this.dialogFormVisible = false;
            //添加完毕后刷新数据
            axios.post("/manage/ann/getAnn/" + this.currentPage, {
              topicInput: this.topicInput,
              managerInput: this.managerInput,
              dataValue: this.dataValue
            }).then((res) => {
              this.$data.annData = res.data.data.records;
            });
            axios.post("/manage/ann/getAnnSearchCount", {
              topicInput: this.topicInput,
              managerInput: this.managerInput,
              dataValue: this.dataValue
            }).then((res) => {
              // console.log(res);
              this.total = res.data.data;
            });
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    deleteAnn(item) {
      axios.post("/manage/ann/deleteAnn", item).then((res) => {
        this.$message({
          message: '删除成功',
          type: 'success'
        });
        //删除完毕后，重新请求该页数据并设置数据。
        axios.post("/manage/ann/getAnn/" + this.currentPage, {
          topicInput: this.topicInput,
          managerInput: this.managerInput,
          dataValue: this.dataValue
        }).then((res) => {
          this.$data.annData = res.data.data.records;
        });
        axios.post("/manage/ann/getAnnSearchCount", {
          topicInput: this.topicInput,
          managerInput: this.managerInput,
          dataValue: this.dataValue
        }).then((res) => {
          // console.log(res);
          this.total = res.data.data;
        });
      });

    }
  },


  created() {
    // 创建的时候默认查询第一页以及用户的数量
    // 伪造一下，后续每次点击分页页码就再次查询
    axios.post("/manage/ann/getAnn/1", {
      topicInput: this.topicInput,
      managerInput: this.managerInput,
      dataValue: this.dataValue
    }).then((res) => {
      // console.log(res);
      this.$data.annData = res.data.data.records;
    });
    //查询所有用户的数量
    axios.post("/manage/ann/getAllAnnCount").then((res) => {
      // console.log(res);
      this.$data.total = res.data.data;
    });
  }
};
</script>