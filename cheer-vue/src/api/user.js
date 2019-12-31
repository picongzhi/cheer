import request from '@/utils/request'

export function getUserInfo(id) {
  return request({
    url: '/user/' + id,
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
