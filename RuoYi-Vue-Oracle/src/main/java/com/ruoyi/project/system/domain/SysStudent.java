package com.ruoyi.project.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 學生对象 sys_student
 *
 * @author ruoyi
 * @date 2020-06-30
 */
public class SysStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 學生id */
    private Long studentId;

    /** 學生姓名 */
    @Excel(name = "學生姓名")
    private String studentName;

    /** 學生年齡 */
    @Excel(name = "學生年齡")
    private Long studentAge;

    /** 興趣愛好 */
    @Excel(name = "興趣愛好")
    private String studentHobby;

    /** 學生性別 */
    @Excel(name = "學生性別",readConverterExp = "0=男,1=女,2=未知")
    private String studentSex;

    /** 狀態 */
    @Excel(name = "狀態",readConverterExp = "0=退學，1=正常，2=休學")
    private String studentStatus;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date studentBirthday;

    public void setStudentId(Long studentId)
    {
        this.studentId = studentId;
    }

    public Long getStudentId()
    {
        return studentId;
    }
    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public String getStudentName()
    {
        return studentName;
    }
    public void setStudentAge(Long studentAge)
    {
        this.studentAge = studentAge;
    }

    public Long getStudentAge()
    {
        return studentAge;
    }
    public void setStudentHobby(String studentHobby)
    {
        this.studentHobby = studentHobby;
    }

    public String getStudentHobby()
    {
        return studentHobby;
    }
    public void setStudentSex(String studentSex)
    {
        this.studentSex = studentSex;
    }

    public String getStudentSex()
    {
        return studentSex;
    }
    public void setStudentStatus(String studentStatus)
    {
        this.studentStatus = studentStatus;
    }

    public String getStudentStatus()
    {
        return studentStatus;
    }
    public void setStudentBirthday(Date studentBirthday)
    {
        this.studentBirthday = studentBirthday;
    }

    public Date getStudentBirthday()
    {
        return studentBirthday;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("studentId", getStudentId())
                .append("studentName", getStudentName())
                .append("studentAge", getStudentAge())
                .append("studentHobby", getStudentHobby())
                .append("studentSex", getStudentSex())
                .append("studentStatus", getStudentStatus())
                .append("studentBirthday", getStudentBirthday())
                .append("remark", getRemark())
                .toString();
    }
}
