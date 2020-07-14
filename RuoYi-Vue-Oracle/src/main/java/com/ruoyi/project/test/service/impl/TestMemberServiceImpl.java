package com.ruoyi.project.test.service.impl;

import java.util.List;

import com.ruoyi.project.test.domain.TestMember;
import com.ruoyi.project.test.mapper.TestMemberMapper;
import com.ruoyi.project.test.service.ITestMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 年纪成员Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-07
 */
@Service
public class TestMemberServiceImpl implements ITestMemberService
{
    @Autowired
    private TestMemberMapper testMemberMapper;

    /**
     * 查询年纪成员
     * 
     * @param memberId 年纪成员ID
     * @return 年纪成员
     */
    @Override
    public TestMember selectTestMemberById(Long memberId)
    {
        return testMemberMapper.selectTestMemberById(memberId);
    }

    /**
     * 查询年纪成员列表
     * 
     * @param testMember 年纪成员
     * @return 年纪成员
     */
    @Override
    public List<TestMember> selectTestMemberList(TestMember testMember)
    {
        return testMemberMapper.selectTestMemberList(testMember);
    }

    /**
     * 新增年纪成员
     * 
     * @param testMember 年纪成员
     * @return 结果
     */
    @Override
    public int insertTestMember(TestMember testMember)
    {
        return testMemberMapper.insertTestMember(testMember);
    }

    /**
     * 修改年纪成员
     * 
     * @param testMember 年纪成员
     * @return 结果
     */
    @Override
    public int updateTestMember(TestMember testMember)
    {
        return testMemberMapper.updateTestMember(testMember);
    }

    /**
     * 批量删除年纪成员
     * 
     * @param memberIds 需要删除的年纪成员ID
     * @return 结果
     */
    @Override
    public int deleteTestMemberByIds(Long[] memberIds)
    {
        return testMemberMapper.deleteTestMemberByIds(memberIds);
    }

    /**
     * 删除年纪成员信息
     * 
     * @param memberId 年纪成员ID
     * @return 结果
     */
    @Override
    public int deleteTestMemberById(Long memberId)
    {
        return testMemberMapper.deleteTestMemberById(memberId);
    }

    @Override
    public List<TestMember> selectTestMemberByGradeId(Long gradeId) {
        return testMemberMapper.selectTestMemberByGradeId(gradeId);
    }
}
