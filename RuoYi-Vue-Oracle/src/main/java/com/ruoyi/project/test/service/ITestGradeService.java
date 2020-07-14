package com.ruoyi.project.test.service;



import java.util.List;

import com.ruoyi.project.test.domain.TestGrade;



/**

 * 年级Service接口

 *

 * @author ruoyi

 * @date 2020-07-09

 */

public interface ITestGradeService

{

    /**

     * 查询年级

     *

     * @param gradeId 年级ID

     * @return 年级

     */

    public TestGrade selectTestGradeById(Long gradeId);



    /**

     * 查询年级列表

     *

     * @param testGrade 年级

     * @return 年级集合

     */

    public List<TestGrade> selectTestGradeList(TestGrade testGrade);



    /**

     * 新增年级

     *

     * @param testGrade 年级

     * @return 结果

     */

    public int insertTestGrade(TestGrade testGrade);



    /**

     * 修改年级

     *

     * @param testGrade 年级

     * @return 结果

     */

    public int updateTestGrade(TestGrade testGrade);



    /**

     * 批量删除年级

     *

     * @param gradeIds 需要删除的年级ID

     * @return 结果

     */

    public int deleteTestGradeByIds(Long[] gradeIds);



    /**

     * 删除年级信息

     *

     * @param gradeId 年级ID

     * @return 结果

     */

    public int deleteTestGradeById(Long gradeId);

}
