package com.ruoyi.project.test.domain;



import org.apache.commons.lang3.builder.ToStringBuilder;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;

import com.ruoyi.framework.web.domain.BaseEntity;



/**

 * 年级对象 test_grade

 *

 * @author ruoyi

 * @date 2020-07-09

 */

public class TestGrade extends BaseEntity

{

    private static final long serialVersionUID = 1L;



    /** 年级编号 */

    private Long gradeId;



    /** 年级姓名 */

    @Excel(name = "年级姓名")

    private String gradeName;



    /** 年级负责人 */

    @Excel(name = "年级负责人")

    private String gradeTeacher;



    /** 开办时间 */

    @Excel(name = "开办时间")

    private Long gradeAge;



    /** 地址 */

    @Excel(name = "地址")

    private String address;



    /** 信息 */

    @Excel(name = "信息")

    private String gradeMessage;



    public void setGradeId(Long gradeId)

    {

        this.gradeId = gradeId;

    }



    public Long getGradeId()

    {

        return gradeId;

    }

    public void setGradeName(String gradeName)

    {

        this.gradeName = gradeName;

    }



    public String getGradeName()

    {

        return gradeName;

    }

    public void setGradeTeacher(String gradeTeacher)

    {

        this.gradeTeacher = gradeTeacher;

    }



    public String getGradeTeacher()

    {

        return gradeTeacher;

    }

    public void setGradeAge(Long gradeAge)

    {

        this.gradeAge = gradeAge;

    }



    public Long getGradeAge()

    {

        return gradeAge;

    }

    public void setAddress(String address)

    {

        this.address = address;

    }



    public String getAddress()

    {

        return address;

    }

    public void setGradeMessage(String gradeMessage)

    {

        this.gradeMessage = gradeMessage;

    }



    public String getGradeMessage()

    {

        return gradeMessage;

    }



    @Override

    public String toString() {

        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)

                .append("gradeId", getGradeId())

                .append("gradeName", getGradeName())

                .append("gradeTeacher", getGradeTeacher())

                .append("gradeAge", getGradeAge())

                .append("address", getAddress())

                .append("gradeMessage", getGradeMessage())

                .toString();

    }

}
