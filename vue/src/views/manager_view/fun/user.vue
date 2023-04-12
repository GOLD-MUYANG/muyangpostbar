<template>
  <div>
    <el-table
        :data="tableData"
        style="width: 100%"
    >
      <el-table-column
          label="邮箱"
          prop="email">
      </el-table-column>
      <el-table-column
          label="密码"
          prop="password">
      </el-table-column>
      <el-table-column
          label="状态"
          prop="state"
          :formatter="messageTypeFormat"
      >
      </el-table-column>
      <el-table-column
          label="真实姓名"
          prop="realName">
      </el-table-column>
      <el-table-column
          label="昵称"
          prop="nickName">
      </el-table-column>
      <el-table-column
          label="头像"
          prop="head">
        <template #default="scope">
          <img style="width:40px" :src=loadImg(scope.row.head)>
        </template>
      </el-table-column>
      <el-table-column>

      </el-table-column>

      <el-table-column
          align="right" width="400px">
        <!--        模糊搜索框-->
        <template slot="header" slot-scope="scope">
          <el-row>
            <el-col :span=5>
              <el-select v-model="value" placeholder="状态" size="mini">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span=16 align="left">
              <el-input
                  v-model="searchWord"
                  size="mini"
                  placeholder="模糊搜索"/>
            </el-col>
            <el-col :span=3>
              <el-button type="info" size="mini" @click="search">搜索</el-button>
            </el-col>
          </el-row>
        </template>
        <!--        用户信息操作-->
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="success"
              @click="dialogVisible = true;setChange(scope.$index,scope.row)"
              style="margin-right: 10px"
          >编辑
          </el-button>
          <el-dialog title="选择恢复默认的条目" :visible.sync="dialogVisible">
            <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选
            </el-checkbox>
            <div style="margin: 15px 0;"></div>
            <el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange">
              <el-checkbox v-for="city in cities" :label="city" :key="city" @click="lo">{{ city }}</el-checkbox>
            </el-checkbox-group>

            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="changeToMoRen(scope.$index, scope.row);dialogVisible = false;">确 定
            </el-button>

          </el-dialog>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--    分页-->
    <!--        total是页码的数量，从数据库拿出来设置给它-->
    <!--    page-size只是用来计算一共有多少页的，不是显示的效果-->
    <el-pagination
        class="paginationClass"
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size="8"
        layout="prev, pager, next"
        :total=total>
    </el-pagination>
    <!--    分页结束-->
  </div>
</template>
<style>
.paginationClass {
  position: fixed;
  bottom: 0;
  height: 40px;
  width: 100%;
  left: -1%
}
</style>
<script>

const cityOptions = ['昵称', '头像', '状态'];
export default {
  data() {
    return {
      // tableData是一个列表，请求后返回给他一个用户信息列表

      tableData: [
        {
          email: '',
          password: '',
          state: '',
          realName: '',
          nickName: '',
          head: ''
        }],
      total: 0,
      searchWord: '',
      //显示的默认初始页数
      currentPage: 1,
      dialogVisible: false,
      checkAll: false,
      checkedCities: ['昵称'],
      cities: cityOptions,
      isIndeterminate: true,
      //编辑功能所需要传输的json
      change: {
        email: '',
        password: '',
        nickName: '',
        head: '',
        state: ''
      },
      options: [{
        value: '正常',
        label: '正常'
      }, {
        value: '禁用',
        label: '禁用'
      }
      ],
      value: ''
    }
  },
  methods: {
    setChange(index,row){
      console.log(row)
      this.change.email = row.email;
      this.change.password = row.password;
      this.change.head=row.head;
      this.change.state = row.state;
      this.change.nickName = row.nickName;
    },
    lo(row) {
      this.$data.change.email = row.email;
      this.$data.change.password = row.password;
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      if (row.state != 0) {
        this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //用户更新数据后得重新查询出来该用户的信息，
          //嵌套深一点的数据只能在一开始赋值，否则最好直接赋值一个对象
          axios.post("/manage/deleteUser", row).then(() => {
            //请求一个页的数据,好让数据刷新
            axios.post("/manage/gotUser/" + this.currentPage, {
              searchWord: this.searchWord,
              value: this.value
            }).then((res) => {
              this.$data.tableData = res.data.data.records;
            })
          })
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      } else {
        alert("已被删除")
      }
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    //包括分页，查找时都要带上模糊搜索的内容
    handleCurrentChange(val) {
      // 点击分页页码后，进行需要查的页码查询
      // console.log(`当前页: ${val}`);
      // console.log("/manage/gotUser/"+`${val}`)
      axios.post("/manage/gotUser/" + `${val}`, {searchWord: this.searchWord, value: this.value}).then((res) => {
        // console.log(res)
        this.$data.tableData = res.data.data.records;
      });
      axios.post("/manage/gotUserSearchCount", {searchWord: this.searchWord, value: this.value}).then((res) => {
        // console.log(res);
        this.$data.total = res.data.data;
      });
    },

    //state显示
    messageTypeFormat(row, column) {
      if (row.state === 0) {
        return '禁用'
      } else {
        return '正常'
      }
    },
    handleCheckAllChange(val) {
      this.checkedCities = val ? cityOptions : [];
      this.isIndeterminate = false;
    },
    handleCheckedCitiesChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.cities.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
    },
    changeToMoRen(index, row) {
      // console.log(row)
      // console.log(index)
      if (this.checkedCities.includes("状态")) {
        this.$data.change.state = 1;
      }
      if (this.checkedCities.includes("头像")) {
        this.$data.change.head = "morenHead.jpg";
      }
      if (this.checkedCities.includes("昵称")) {
        this.$data.change.nickName = "morenNickName";
      }
      // console.log(row)
      // console.log(this.change)
      axios.post("/manage/changeToMoRen", this.change).then((res) => {
        this.$message({
          message:'修改成功',
          type:'success'
        }),
            axios.post("/manage/gotUser/" + this.currentPage, {searchWord: this.searchWord, value: this.value}).then((res) => {
              // console.log(res)
              this.$data.tableData = res.data.data.records;
            });
        axios.post("/manage/gotUserSearchCount", {searchWord: this.searchWord, value: this.value}).then((res) => {
          // console.log(res);
          this.$data.total = res.data.data;
        });
      })
    },
    search() {
      // console.log(this.value)
      // console.log(searchWord);
      axios.post("/manage/fuzzySearch", {searchWord: this.searchWord, value: this.value}).then((res) => {
        console.log(res);
        this.tableData = res.data.data.records;
      });
      axios.post("/manage/gotUserSearchCount", {searchWord: this.searchWord, value: this.value}).then((res) => {
        // console.log(res);
        this.$data.total = res.data.data;
      });
    },
    loadImg(path){
      try {
          return require("@/assets/userHead/"+path);
      }catch (e){
      }
    }
  },
  created(searchWord, value) {
    // 创建的时候默认查询第一页以及用户的数量
    // 伪造一下，后续每次点击分页页码就再次查询
    axios.post("/manage/gotUser/1", {searchWord: searchWord, value: value}).then((res) => {
      // console.log(res);
      this.$data.tableData = res.data.data.records;

    });
    //查询所有用户的数量
    axios.get("/manage/gotUserCount").then((res) => {
      // console.log(res);
      this.$data.total = res.data.data;
    });
  }
}
</script>