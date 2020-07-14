package com.ruoyi.project.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 信息对象 message
 * 
 * @author ruoyi
 * @date 2020-07-09
 */
public class Message extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 个人编号 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 工作信息 */
    @Excel(name = "工作信息")
    private String jobMessage;

    /** 学习信息 */
    @Excel(name = "学习信息")
    private String studyMessage;

    /** 健康信息 */
    @Excel(name = "健康信息")
    private String healthMessage;

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
    public void setJobMessage(String jobMessage) 
    {
        this.jobMessage = jobMessage;
    }

    public String getJobMessage() 
    {
        return jobMessage;
    }
    public void setStudyMessage(String studyMessage) 
    {
        this.studyMessage = studyMessage;
    }

    public String getStudyMessage() 
    {
        return studyMessage;
    }
    public void setHealthMessage(String healthMessage) 
    {
        this.healthMessage = healthMessage;
    }

    public String getHealthMessage() 
    {
        return healthMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("jobMessage", getJobMessage())
            .append("studyMessage", getStudyMessage())
            .append("healthMessage", getHealthMessage())
            .toString();
    }
}
