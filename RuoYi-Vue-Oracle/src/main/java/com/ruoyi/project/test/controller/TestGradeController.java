package com.ruoyi.project.test.controller;



import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.framework.aspectj.lang.annotation.Log;

import com.ruoyi.framework.aspectj.lang.enums.BusinessType;

import com.ruoyi.project.test.domain.TestGrade;

import com.ruoyi.project.test.service.ITestGradeService;

import com.ruoyi.framework.web.controller.BaseController;

import com.ruoyi.framework.web.domain.AjaxResult;

import com.ruoyi.common.utils.poi.ExcelUtil;

import com.ruoyi.framework.web.page.TableDataInfo;



/**

 * 年级Controller

 *

 * @author ruoyi

 * @date 2020-07-09

 */

@RestController

@RequestMapping("/test/grade")

public class TestGradeController extends BaseController

{

    @Autowired

    private ITestGradeService testGradeService;



    /**

     * 查询年级列表

     */

    @PreAuthorize("@ss.hasPermi('test:grade:list')")

    @GetMapping("/list")

    public TableDataInfo list(TestGrade testGrade)

    {

        startPage();

        List<TestGrade> list = testGradeService.selectTestGradeList(testGrade);

        return getDataTable(list);

    }



    /**

     * 导出年级列表

     */

    @PreAuthorize("@ss.hasPermi('test:grade:export')")

    @Log(title = "年级", businessType = BusinessType.EXPORT)

    @GetMapping("/export")

    public AjaxResult export(TestGrade testGrade)

    {

        List<TestGrade> list = testGradeService.selectTestGradeList(testGrade);

        ExcelUtil<TestGrade> util = new ExcelUtil<TestGrade>(TestGrade.class);

        return util.exportExcel(list, "grade");

    }



    /**

     * 获取年级详细信息

     */

    @PreAuthorize("@ss.hasPermi('test:grade:query')")

    @GetMapping(value = "/{gradeId}")

    public AjaxResult getInfo(@PathVariable("gradeId") Long gradeId)

    {

        return AjaxResult.success(testGradeService.selectTestGradeById(gradeId));

    }



    /**

     * 新增年级

     */

    @PreAuthorize("@ss.hasPermi('test:grade:add')")

    @Log(title = "年级", businessType = BusinessType.INSERT)

    @PostMapping

    public AjaxResult add(@RequestBody TestGrade testGrade)

    {

        return toAjax(testGradeService.insertTestGrade(testGrade));

    }



    /**

     * 修改年级

     */

    @PreAuthorize("@ss.hasPermi('test:grade:edit')")

    @Log(title = "年级", businessType = BusinessType.UPDATE)

    @PutMapping

    public AjaxResult edit(@RequestBody TestGrade testGrade)

    {

        return toAjax(testGradeService.updateTestGrade(testGrade));

    }



    /**

     * 删除年级

     */

    @PreAuthorize("@ss.hasPermi('test:grade:remove')")

    @Log(title = "年级", businessType = BusinessType.DELETE)

    @DeleteMapping("/{gradeIds}")

    public AjaxResult remove(@PathVariable Long[] gradeIds)

    {

        return toAjax(testGradeService.deleteTestGradeByIds(gradeIds));

    }

}
