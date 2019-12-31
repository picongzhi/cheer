import request from '@/utils/request'

export function getUserInfo() {
  return request({
    url: '/user',
    method: 'get'
  })
}

export function userLogin(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data: data
  })
}
