<template>
  <div>

    <!--  我想要把今天增长的人数和昨天增长的人数，即近期增长的人数查出来，用百分比表示
    折线，用echarts。查询几天的数据-->
    <div>
      <el-row :gutter="20" type="flex" justify="center">
        <el-col :span="6">
          <div>
            <el-statistic group-separator="," :value="barCount" title="总吧数"></el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div>
            <el-statistic group-separator="," :value="invitationCount" title="总帖子数"></el-statistic>
          </div>
        </el-col>
      </el-row>
      <el-row justify="center" type="flex" style="margin-top: 100px">
        <el-col :span="15">
          <div class="Echarts">
            <div id="main" style="width: 800px;height:400px;">aa</div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      barCount: 0,
      invitationCount: 0,
      series:
          [{
            name: '用户增长量',
            type: 'line',
            stack: 'Total',
            data: [],
            itemStyle: {
              normal: {
                label: {
                  show: true,
                  position: 'top',
                  formatter: '{b}\n{c}'
                }
              }
            },
          },
            {
              name: '吧增长量',
              type: 'line',
              data: [],
              itemStyle: {
                normal: {
                  label: {
                    show: true,
                    position: 'top',
                    formatter: '{b}\n{c}'
                  }
                }
              },
            },
          ]
    };
  },
  methods: {
    myEcharts() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById('main'));

      // 指定图表的配置项和数据
      var option = {
        title: {
          text: '近期网站数据变化'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['用户增长量', '吧增长量']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ["七天前", '六天前', '五天前', '四天前', '三天前', '二天前', '一天前']
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            show: true,
            interval: 'auto',
            formatter: '{value}'
          },
          show: true
        },
        series: this.series
      };
      // 使用刚指定的配置项和数据显示图表。
      axios.post("/manage/welcome/getUserIncrease").then((res) => {
        // console.log(res)
        this.series[0].data = res.data.data;

      })
      axios.post("/manage/welcome/getBarIncrease").then((res) => {
        // console.log(res)
        this.series[1].data = res.data.data;
        myChart.setOption(option)
      })


    }
  },
  created() {
    axios.get("/manage/welcome/getAllBarCount").then((res) => {
      this.barCount = res.data.data;
    })
    axios.get("/manage/welcome/getAllInvitationCount").then((res) => {
      this.invitationCount = res.data.data;
    })

    axios.post("/manage/welcome/getBarIncrease").then((res) => {
      // console.log(res)
      this.series[1].data = res.data.data;
    })

  },
  mounted() {
    this.myEcharts();
  }
};
</script>
<style>
.like {
  cursor: pointer;
  font-size: 25px;
  display: inline-block;
}
</style>