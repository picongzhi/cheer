import Home from '@/views/Home.vue'

export default [{
  path: '/',
  name: 'home',
  component: Home
}, {
  path: '/login',
  name: 'login',
  component: () => import( /* webpackChunkName: "login" */ '@/views/Login.vue')
}, {
  path: '/register',
  name: 'register',
  component: () => import( /* webpackChunkName: "register" */ '@/views/Register.vue')
}]
