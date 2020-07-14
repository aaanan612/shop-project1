package com.ruoyi.project.test.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 学生对象 student
 * 
 * @author ruoyi
 * @date 2020-07-03
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生编号 */
    private Long id;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String name;

    /** 学生年龄 */
    @Excel(name = "学生年龄")
    private String sex;

    /** 学生生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "学生生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .toString();
    }
}
