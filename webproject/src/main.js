import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './Css/global.css'
import {VueJsonp} from 'vue-jsonp'
import dataV from '@jiaminghi/data-view'
import BaiduMap from 'vue-baidu-map'

Vue.use(BaiduMap, {
  ak: 'bRmZ5se6r2Q47lLBzLPzTeyPIvZBPGws'
});

Vue.use(dataV);
Vue.use(VueJsonp);

Vue.config.productionTip = false;
Vue.use(ElementUI);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');

