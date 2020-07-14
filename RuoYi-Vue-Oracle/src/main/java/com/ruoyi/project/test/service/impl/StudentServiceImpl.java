package com.ruoyi.project.test.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.test.mapper.StudentMapper;
import com.ruoyi.project.test.domain.Student;
import com.ruoyi.project.test.service.IStudentService;

/**
 * 学生Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-03
 */
@Service
public class StudentServiceImpl implements IStudentService 
{
//    日志
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private StudentMapper studentMapper;


    /**
     * 查询学生
     * 
     * @param id 学生ID
     * @return 学生
     */
    @Override
    public Student selectStudentById(Long id)
    {
        return studentMapper.selectStudentById(id);
    }

    /**
     * 查询学生列表
     * 
     * @param student 学生
     * @return 学生
     */
    @Override
    public List<Student> selectStudentList(Student student)
    {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 新增学生
     * 
     * @param student 学生
     * @return 结果
     */
    @Override
    public int insertStudent(Student student)
    {
        return studentMapper.insertStudent(student);
    }

    /**
     * 修改学生
     * 
     * @param student 学生
     * @return 结果
     */
    @Override
    public int updateStudent(Student student)
    {
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学生
     * 
     * @param ids 需要删除的学生ID
     * @return 结果
     */
    @Override
    public int deleteStudentByIds(Long[] ids)
    {
        return studentMapper.deleteStudentByIds(ids);
    }

    /**
     * 删除学生信息
     * 
     * @param id 学生ID
     * @return 结果
     */
    @Override
    public int deleteStudentById(Long id)
    {
        return studentMapper.deleteStudentById(id);
    }


    //  导入学生数据
    @Override
    public String importStudent(List<Student> studentList, boolean updateSupport) {
        if(StringUtils.isNull(studentList)||studentList.size()==0){
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Student student : studentList)
        {
            try
            {
                // 验证是否存在这个用户
                Student s = studentMapper.selectStudentByName(student.getName());
                // 数据库是否存在此数据
                if(StringUtils.isNull(s)){
                    this.insertStudent(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + student.getName() + " 导入成功");
                }
                //看设置是否覆盖
                //支持覆盖
                else if (updateSupport)
                {
                    this.updateStudent(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + student.getName() + " 更新成功");
                }
                //不支持覆盖
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + student.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + student.getName() + " 导入失败：";
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
