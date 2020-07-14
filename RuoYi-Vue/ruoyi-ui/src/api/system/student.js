import request from '@/utils/request'

// 查询學生列表
export function listStudent(query) {
  return request({
    url: '/system/student/list',
    method: 'get',
    params: query
  })
}

// 查询學生详细
export function getStudent(studentId) {
  return request({
    url: '/system/student/' + studentId,
    method: 'get'
  })
}

// 新增學生
export function addStudent(data) {
  return request({
    url: '/system/student',
    method: 'post',
    data: data
  })
}

// 修改學生
export function updateStudent(data) {
  return request({
    url: '/system/student',
    method: 'put',
    data: data
  })
}

// 删除學生
export function delStudent(studentId) {
  return request({
    url: '/system/student/' + studentId,
    method: 'delete'
  })
}

// 导出學生
export function exportStudent(query) {
  return request({
    url: '/system/student/export',
    method: 'get',
    params: query
  })
}

  // 下载用户导入模板
  export function importTemplate() {
    return request({
      url: '/system/student/importTemplate',
      method: 'get'
    })
  }

