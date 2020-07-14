import request from '@/utils/request'



// 查询年级列表

export function listGrade(query) {

  return request({

    url: '/test/grade/list',

    method: 'get',

    params: query

  })

}



// 查询年级详细

export function getGrade(gradeId) {

  return request({

    url: '/test/grade/' + gradeId,

    method: 'get'

  })

}



// 新增年级

export function addGrade(data) {

  return request({

    url: '/test/grade',

    method: 'post',

    data: data

  })

}



// 修改年级

export function updateGrade(data) {

  return request({

    url: '/test/grade',

    method: 'put',

    data: data

  })

}



// 删除年级

export function delGrade(gradeId) {

  return request({

    url: '/test/grade/' + gradeId,

    method: 'delete'

  })

}



// 导出年级

export function exportGrade(query) {

  return request({

    url: '/test/grade/export',

    method: 'get',

    params: query

  })

}
