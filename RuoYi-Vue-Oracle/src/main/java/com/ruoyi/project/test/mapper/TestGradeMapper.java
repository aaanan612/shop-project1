package com.ruoyi.project.test.mapper;



import java.util.List;

import com.ruoyi.project.test.domain.TestGrade;



/**

 * 年级Mapper接口

 *

 * @author ruoyi

 * @date 2020-07-09

 */

public interface TestGradeMapper

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

     * 删除年级

     *

     * @param gradeId 年级ID

     * @return 结果

     */

    public int deleteTestGradeById(Long gradeId);



    /**

     * 批量删除年级

     *

     * @param gradeIds 需要删除的数据ID

     * @return 结果

     */

    public int deleteTestGradeByIds(Long[] gradeIds);

}
