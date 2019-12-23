import Vue from 'vue'
import VueRouter from 'vue-router'
import homeRouterConfig from './home'
import userRouterConfig from './user'

Vue.use(VueRouter)

// const routes = [...homeRouterConfig, ...userRouterConfig]

let routes = []
// 使用webpack上下文扫描自动加载路由
const routerContext = require.context('./', true, /index\.js$/)
routerContext.keys().forEach(route => {
  if (route.startsWith('./index')) {
    return
  }

  const routerModule = routerContext(route)
  routes = [...routes, ...(routerModule.default || routerModule)]
})

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
