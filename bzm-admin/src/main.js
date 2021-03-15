import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
//引入全局样式
import './assets/css/global.css'
import './assets/font/iconfont.css'
import axios from 'axios';

Vue.prototype.$http = axios;
// 设置请求的根路径
axios.defaults.baseURL = "http://localhost:8989/"

Vue.config.productionTip = false

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
