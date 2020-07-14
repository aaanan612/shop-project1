import request from '@/utils/request'

// 查询信息列表
export function listMessage(query) {
  return request({
    url: '/system/message/list',
    method: 'get',
    params: query
  })
}

// 查询信息详细
export function getMessage(id) {
  return request({
    url: '/system/message/' + id,
    method: 'get'
  })
}

// 新增信息
export function addMessage(data) {
  return request({
    url: '/system/message',
    method: 'post',
    data: data
  })
}

// 修改信息
export function updateMessage(data) {
  return request({
    url: '/system/message',
    method: 'put',
    data: data
  })
}

// 删除信息
export function delMessage(id) {
  return request({
    url: '/system/message/' + id,
    method: 'delete'
  })
}

// 导出信息
export function exportMessage(query) {
  return request({
    url: '/system/message/export',
    method: 'get',
    params: query
  })
}
