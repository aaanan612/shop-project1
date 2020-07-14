package com.ruoyi.project.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysStudentMapper;
import com.ruoyi.project.system.domain.SysStudent;
import com.ruoyi.project.system.service.ISysStudentService;

/**
 * 學生Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-01
 */
@Service
public class SysStudentServiceImpl implements ISysStudentService
{
    private static final Logger log = LoggerFactory.getLogger(SysStudentServiceImpl.class);

    @Autowired
    private SysStudentMapper sysStudentMapper;

    /**
     * 查询學生
     *
     * @param studentId 學生ID
     * @return 學生
     */
    @Override
    public SysStudent selectSysStudentById(Long studentId)
    {
        return sysStudentMapper.selectSysStudentById(studentId);
    }

    /**
     * 查询學生列表
     *
     * @param sysStudent 學生
     * @return 學生
     */
    @Override
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent)
    {
        return sysStudentMapper.selectSysStudentList(sysStudent);
    }

    /**
     * 新增學生
     *
     * @param sysStudent 學生
     * @return 结果
     */
    @Override
    public int insertSysStudent(SysStudent sysStudent)
    {
        return sysStudentMapper.insertSysStudent(sysStudent);
    }

    /**
     * 修改學生
     *
     * @param sysStudent 學生
     * @return 结果
     */
    @Override
    public int updateSysStudent(SysStudent sysStudent)
    {
        return sysStudentMapper.updateSysStudent(sysStudent);
    }

    /**
     * 批量删除學生
     *
     * @param studentIds 需要删除的學生ID
     * @return 结果
     */
    @Override
    public int deleteSysStudentByIds(Long[] studentIds)
    {
        return sysStudentMapper.deleteSysStudentByIds(studentIds);
    }

    /**
     * 删除學生信息
     *
     * @param studentId 學生ID
     * @return 结果
     */
    @Override
    public int deleteSysStudentById(Long studentId)
    {
        return sysStudentMapper.deleteSysStudentById(studentId);
    }



//  导入学生数据
    @Override
    public String importStudent(List<SysStudent> studentList, boolean updateSupport) {
        if(StringUtils.isNull(studentList)||studentList.size()==0){
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SysStudent student : studentList)
        {
            try
            {
                // 验证是否存在这个用户
                SysStudent s = sysStudentMapper.selectSysStudentByStudentName(student.getStudentName());
                // 数据库是否存在此数据
                if(StringUtils.isNull(s)){
                    this.insertSysStudent(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + student.getStudentName() + " 导入成功");
                }
                //看设置是否覆盖
                    //支持覆盖
                else if (updateSupport)
                {
                    this.updateSysStudent(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + student.getStudentName() + " 更新成功");
                }
                    //不支持覆盖
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + student.getStudentName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + student.getStudentName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
