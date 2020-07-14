package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.SysStudent;

/**
 * 學生Mapper接口
 *
 * @author ruoyi
 * @date 2020-07-01
 */
public interface SysStudentMapper
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
     * 删除學生
     *
     * @param studentId 學生ID
     * @return 结果
     */
    public int deleteSysStudentById(Long studentId);

    /**
     * 批量删除學生
     *
     * @param studentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysStudentByIds(Long[] studentIds);


    //根据学生姓名查找数据
	SysStudent selectSysStudentByStudentName(String studentName);
}
