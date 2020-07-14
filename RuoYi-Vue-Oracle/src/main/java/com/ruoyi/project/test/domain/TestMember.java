package com.ruoyi.project.test.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 年纪成员对象 test_member
 * 
 * @author ruoyi
 * @date 2020-07-07
 */
public class TestMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成员编号 */
    private Long memberId;

    /** 年级成员姓名 */
    @Excel(name = "年级成员姓名")
    private String memberName;

    /** 年级编号 */
    @Excel(name = "年级编号")
    private Long gradeId;

    /** 成员状态 */
    @Excel(name = "成员状态")
    private String memberStatus;

    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
    }
    public void setMemberName(String memberName) 
    {
        this.memberName = memberName;
    }

    public String getMemberName() 
    {
        return memberName;
    }
    public void setGradeId(Long gradeId) 
    {
        this.gradeId = gradeId;
    }

    public Long getGradeId() 
    {
        return gradeId;
    }
    public void setMemberStatus(String memberStatus) 
    {
        this.memberStatus = memberStatus;
    }

    public String getMemberStatus() 
    {
        return memberStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("memberId", getMemberId())
            .append("memberName", getMemberName())
            .append("gradeId", getGradeId())
            .append("memberStatus", getMemberStatus())
            .toString();
    }
}
