import request from '@/utils/request'

// 查询商品列表
export function listGoods(query) {
  return request({
    url: '/test/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商品详细
export function getGoods(goodsId) {
  return request({
    url: '/test/goods/' + goodsId,
    method: 'get'
  })
}

// 新增商品
export function addGoods(data) {
  return request({
    url: '/test/goods',
    method: 'post',
    data: data
  })
}

// 修改商品
export function updateGoods(data) {
  return request({
    url: '/test/goods',
    method: 'put',
    data: data
  })
}

// 删除商品
export function delGoods(goodsId) {
  return request({
    url: '/test/goods/' + goodsId,
    method: 'delete'
  })
}

// 导出商品
export function exportGoods(query) {
  return request({
    url: '/test/goods/export',
    method: 'get',
    params: query
  })
}