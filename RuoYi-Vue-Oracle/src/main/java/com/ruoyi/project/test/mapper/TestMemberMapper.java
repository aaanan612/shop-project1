package com.ruoyi.project.test.mapper;

import com.ruoyi.project.test.domain.TestMember;

import java.util.List;

/**
 * 年纪成员Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-07
 */
public interface TestMemberMapper 
{
    /**
     * 查询年纪成员
     * 
     * @param memberId 年纪成员ID
     * @return 年纪成员
     */
    public TestMember selectTestMemberById(Long memberId);

    /**
     * 查询年纪成员列表
     * 
     * @param testMember 年纪成员
     * @return 年纪成员集合
     */
    public List<TestMember> selectTestMemberList(TestMember testMember);

    /**
     * 新增年纪成员
     * 
     * @param testMember 年纪成员
     * @return 结果
     */
    public int insertTestMember(TestMember testMember);

    /**
     * 修改年纪成员
     * 
     * @param testMember 年纪成员
     * @return 结果
     */
    public int updateTestMember(TestMember testMember);

    /**
     * 删除年纪成员
     * 
     * @param memberId 年纪成员ID
     * @return 结果
     */
    public int deleteTestMemberById(Long memberId);

    /**
     * 批量删除年纪成员
     * 
     * @param memberIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTestMemberByIds(Long[] memberIds);

    List<TestMember> selectTestMemberByGradeId(Long gradeId);
}
