import request from '@/utils/request'

// 查询客户管理列表
export function listCustomer(query) {
  return request({
    url: '/test/customer/list',
    method: 'get',
    params: query
  })
}

// 查询客户管理详细
export function getCustomer(customerId) {
  return request({
    url: '/test/customer/' + customerId,
    method: 'get'
  })
}

// 新增客户管理
export function addCustomer(data) {
  return request({
    url: '/test/customer',
    method: 'post',
    data: data
  })
}

// 修改客户管理
export function updateCustomer(data) {
  return request({
    url: '/test/customer',
    method: 'put',
    data: data
  })
}

// 删除客户管理
export function delCustomer(customerId) {
  return request({
    url: '/test/customer/' + customerId,
    method: 'delete'
  })
}

// 导出客户管理
export function exportCustomer(query) {
  return request({
    url: '/test/customer/export',
    method: 'get',
    params: query
  })
}