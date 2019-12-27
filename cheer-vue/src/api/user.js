import request from '@/utils/request'

export function getUserInfo(id) {
  return request({
    url: '/user/' + id,
    method: 'get'
  })
}
