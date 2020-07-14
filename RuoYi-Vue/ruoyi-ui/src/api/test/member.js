import request from '@/utils/request'

// 查询年纪成员列表
export function listMember(query) {
  return request({
    url: '/test/member/list',
    method: 'get',
    params: query
  })
}

// 查询年纪成员详细
export function getMember(memberId) {
  return request({
    url: '/test/member/' + memberId,
    method: 'get'
  })
}

// 新增年纪成员
export function addMember(data) {
  return request({
    url: '/test/member',
    method: 'post',
    data: data
  })
}

// 修改年纪成员
export function updateMember(data) {
  return request({
    url: '/test/member',
    method: 'put',
    data: data
  })
}

// 删除年纪成员
export function delMember(memberId) {
  return request({
    url: '/test/member/' + memberId,
    method: 'delete'
  })
}

// 导出年纪成员
export function exportMember(query) {
  return request({
    url: '/test/member/export',
    method: 'get',
    params: query
  })
}
export function getMemberByGradeId(gradeId){
  return request({
    url:'/test/member/getMemberByGradeId/'+gradeId,
    method:'get',
    // params:gradeId
  })
}
