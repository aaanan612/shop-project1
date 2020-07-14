import request from '@/utils/request'

// 查询产品管理列表
export function listProduct(query) {
  return request({
    url: '/test/product/list',
    method: 'get',
    params: query
  })
}

// 查询产品管理详细
export function getProduct(productId) {
  return request({
    url: '/test/product/' + productId,
    method: 'get'
  })
}

// 新增产品管理
export function addProduct(data) {
  return request({
    url: '/test/product',
    method: 'post',
    data: data
  })
}

// 修改产品管理
export function updateProduct(data) {
  return request({
    url: '/test/product',
    method: 'put',
    data: data
  })
}

// 删除产品管理
export function delProduct(productId) {
  return request({
    url: '/test/product/' + productId,
    method: 'delete'
  })
}

// 导出产品管理
export function exportProduct(query) {
  return request({
    url: '/test/product/export',
    method: 'get',
    params: query
  })
}