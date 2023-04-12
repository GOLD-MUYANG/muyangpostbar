import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import "./assets/gloable.css";
import axios from "axios";
import * as echarts from 'echarts'

Vue.prototype.$echarts = echarts;
Vue.config.productionTip = false
Vue.use(ElementUI,{size:"small"});
axios.defaults.baseURL="http://localhost:8080"

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
