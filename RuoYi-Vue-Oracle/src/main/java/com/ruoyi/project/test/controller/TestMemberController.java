package com.ruoyi.project.test.controller;

import java.util.List;

import com.ruoyi.project.test.domain.TestMember;
import com.ruoyi.project.test.service.ITestMemberService;
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
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 年纪成员Controller
 * 
 * @author ruoyi
 * @date 2020-07-07
 */
@RestController
@RequestMapping("/test/member")
public class TestMemberController extends BaseController
{
    @Autowired
    private ITestMemberService testMemberService;

    /**
     * 查询年纪成员列表
     */
    @PreAuthorize("@ss.hasPermi('test:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestMember testMember)
    {
        startPage();
        List<TestMember> list = testMemberService.selectTestMemberList(testMember);
        return getDataTable(list);
    }

    /**
     * 导出年纪成员列表
     */
    @PreAuthorize("@ss.hasPermi('test:member:export')")
    @Log(title = "年纪成员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TestMember testMember)
    {
        List<TestMember> list = testMemberService.selectTestMemberList(testMember);
        ExcelUtil<TestMember> util = new ExcelUtil<TestMember>(TestMember.class);
        return util.exportExcel(list, "member");
    }

    /**
     * 获取年纪成员详细信息
     */
    @PreAuthorize("@ss.hasPermi('test:member:query')")
    @GetMapping(value = "/{memberId}")
    public AjaxResult getInfo(@PathVariable("memberId") Long memberId)
    {
        return AjaxResult.success(testMemberService.selectTestMemberById(memberId));
    }

    /**
     * 新增年纪成员
     */
    @PreAuthorize("@ss.hasPermi('test:member:add')")
    @Log(title = "年纪成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestMember testMember)
    {
        return toAjax(testMemberService.insertTestMember(testMember));
    }

    /**
     * 修改年纪成员
     */
    @PreAuthorize("@ss.hasPermi('test:member:edit')")
    @Log(title = "年纪成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestMember testMember)
    {
        return toAjax(testMemberService.updateTestMember(testMember));
    }

    /**
     * 删除年纪成员
     */
    @PreAuthorize("@ss.hasPermi('test:member:remove')")
    @Log(title = "年纪成员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{memberIds}")
    public AjaxResult remove(@PathVariable Long[] memberIds)
    {
        return toAjax(testMemberService.deleteTestMemberByIds(memberIds));
    }

    /**
     * 获取年纪成员详细信息
     */
    @PreAuthorize("@ss.hasPermi('test:member:getMemberByGradeId')")
    @GetMapping(value = "/getMemberByGradeId/{gradeId}")
    public TableDataInfo getMemberByGradeId(@PathVariable("gradeId") Long gradeId)
    {
        System.out.println("getMemberByGradeId");
        startPage();
        List<TestMember> list = testMemberService.selectTestMemberByGradeId(gradeId);
        return getDataTable(list);
    }


}
