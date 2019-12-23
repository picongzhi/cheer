import axios from "axios"
import router from "../router"
import {
  Message,
  Loading
} from "element-ui"

const service = axios.create({
  timeout: 60000,
  baseURL: process.env.BASE_URL
})

// post请求设置Content-Type
service.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'

let loading = null
// 请求拦截，用于处理需要在请求前的操作
service.interceptors.request.use(config => {
  loading = Loading.service({
    text: '正在加载中...'
  })

  const token = localStorage.getItem('token')
  if (token) {
    config.headers['Authorization'] = token
  }

  return config
}, error => {
  return Promise.reject(error)
})

// 请求响应拦截，用于处理需要在请求返回后的操作
service.interceptors.response.use(response => {
  if (loading) {
    loading.close()
  }

  const responseCode = response.status
  if (responseCode === 200) {
    return Promise.resolve(response)
  } else {
    return Promise.reject(response)
  }
}, error => {
  if (loading) {
    loading.close()
  }

  // 断网或请求超时
  if (!error.response) {
    if (error.message.includes('timeout')) {
      // 请求超时
      console.log('超时了')
      Message.error('请求超时，请检查网络是否连接正常')
    } else {
      // 断网
      console.log('断网了')
      Message.error('请求失败，请检查网络连接是否正常')
    }

    return
  }

  // 返回不是以2开头的情况
  const responseCode = error.response.status
  switch (responseCode) {
    // 未登录
    case 401:
      router.replace({
        path: '/login',
        query: {
          redirect: router.currentRoute.fullPath
        }
      })
      break
      // token过期
    case 403:
      Message({
        type: 'error',
        message: '登录信息过期，请重新登录'
      })
      localStorage.removeItem('token')
      setTimeout(() => {
        router.replace({
          path: '/login',
          query: {
            redirect: router.currentRoute.fullPath
          }
        })
      }, 1000)
      break
    case 404:
      Message({
        type: 'error',
        message: '网络请求不存在'
      })
      break
    default:
      Message({
        type: 'error',
        message: error.response.data.message
      })
  }

  return Promise.reject(error)
})

export default service

// export const uploadFile = formData => {
//   return service.request({
//     method: 'post',
//     url: '/upload',
//     data: formData,
//     headers: {
//       'Content-Type': 'multipart/form-data'
//     }
//   })
// }
