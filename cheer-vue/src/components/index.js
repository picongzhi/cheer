import Vue from 'vue'

// 使用webpack上下文扫描自动加载全局组件
const componentsContext = require.context('./global', true, /\.js$/)
componentsContext.keys().forEach(component => {
  const componentConfig = componentsContext(component)
  const ctrl = componentConfig.default || componentConfig
  Vue.component(ctrl.name, ctrl)
})
