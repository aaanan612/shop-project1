package com.ruoyi.project.test.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ruoyi.project.test.mapper.TestGradeMapper;

import com.ruoyi.project.test.domain.TestGrade;

import com.ruoyi.project.test.service.ITestGradeService;



/**

 * 年级Service业务层处理

 *

 * @author ruoyi

 * @date 2020-07-09

 */

@Service

public class TestGradeServiceImpl implements ITestGradeService {

    @Autowired

    private TestGradeMapper testGradeMapper;


    /**
     * 查询年级
     *
     * @param gradeId 年级ID
     * @return 年级
     */

    @Override

    public TestGrade selectTestGradeById(Long gradeId) {

        return testGradeMapper.selectTestGradeById(gradeId);

    }


    /**
     * 查询年级列表
     *
     * @param testGrade 年级
     * @return 年级
     */

    @Override

    public List<TestGrade> selectTestGradeList(TestGrade testGrade) {

        return testGradeMapper.selectTestGradeList(testGrade);

    }


    /**
     * 新增年级
     *
     * @param testGrade 年级
     * @return 结果
     */

    @Override

    public int insertTestGrade(TestGrade testGrade) {

        return testGradeMapper.insertTestGrade(testGrade);

    }


    /**
     * 修改年级
     *
     * @param testGrade 年级
     * @return 结果
     */

    @Override

    public int updateTestGrade(TestGrade testGrade) {

        return testGradeMapper.updateTestGrade(testGrade);

    }


    /**
     * 批量删除年级
     *
     * @param gradeIds 需要删除的年级ID
     * @return 结果
     */

    @Override

    public int deleteTestGradeByIds(Long[] gradeIds) {

        return testGradeMapper.deleteTestGradeByIds(gradeIds);

    }


    /**
     * 删除年级信息
     *
     * @param gradeId 年级ID
     * @return 结果
     */

    @Override

    public int deleteTestGradeById(Long gradeId) {

        return testGradeMapper.deleteTestGradeById(gradeId);

    }
}
