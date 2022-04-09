// import Vue from 'vue'
import App from './App'

import router from './router'
import store from './store'

import lodash from 'lodash'

// mavonEditor
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

// import ElementUI from 'element-ui'
import '@/assets/theme/index.css'
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/icon/iconfont.css'

import {formatTime} from "./utils/time";
//markdown-it-vue-light (markdown-it的精简版 去掉流程图渲染)



//vue-katex
import VueKatex from "vue-katex";
import Katex from 'vue-katex-auto-render'
import "katex/dist/katex.min.css";
Vue.directive('katex', Katex);

Vue.config.productionTip = false

// Vue.use(ElementUI)
Object.defineProperty(Vue.prototype, '$_', { value: lodash })


Vue.directive('title',  function (el, binding) {
  document.title = el.dataset.title
})
// 格式话时间
Vue.filter('format', formatTime)

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App}
})
