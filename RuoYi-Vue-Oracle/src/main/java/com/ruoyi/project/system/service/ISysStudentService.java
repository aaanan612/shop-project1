package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.SysStudent;

/**
 * 學生Service接口
 *
 * @author ruoyi
 * @date 2020-07-01
 */
public interface ISysStudentService
{
    /**
     * 查询學生
     *
     * @param studentId 學生ID
     * @return 學生
     */
    public SysStudent selectSysStudentById(Long studentId);

    /**
     * 查询學生列表
     *
     * @param sysStudent 學生
     * @return 學生集合
     */
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent);

    /**
     * 新增學生
     *
     * @param sysStudent 學生
     * @return 结果
     */
    public int insertSysStudent(SysStudent sysStudent);

    /**
     * 修改學生
     *
     * @param sysStudent 學生
     * @return 结果
     */
    public int updateSysStudent(SysStudent sysStudent);

    /**
     * 批量删除學生
     *
     * @param studentIds 需要删除的學生ID
     * @return 结果
     */
    public int deleteSysStudentByIds(Long[] studentIds);

    /**
     * 删除學生信息
     *
     * @param studentId 學生ID
     * @return 结果
     */
    public int deleteSysStudentById(Long studentId);


//    创建导入数据

    String importStudent(List<SysStudent> studentList, boolean updateSupport);
}
