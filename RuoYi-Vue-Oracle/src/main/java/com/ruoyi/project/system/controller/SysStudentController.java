package com.ruoyi.project.system.controller;

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
import com.ruoyi.project.system.domain.SysStudent;
import com.ruoyi.project.system.service.ISysStudentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 學生Controller
 *
 * @author ruoyi
 * @date 2020-07-01
 */
@RestController
@RequestMapping("/system/student")
public class SysStudentController extends BaseController
{
    @Autowired
    private ISysStudentService sysStudentService;

    /**
     * 查询學生列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysStudent sysStudent)
    {
        startPage();
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        return getDataTable(list);
    }

    /**
     * 导出學生列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:export')")
    @Log(title = "學生", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysStudent sysStudent)
    {
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
        return util.exportExcel(list, "student");
    }

    /**
     * 获取學生详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return AjaxResult.success(sysStudentService.selectSysStudentById(studentId));
    }

    /**
     * 新增學生
     */
    @PreAuthorize("@ss.hasPermi('system:student:add')")
    @Log(title = "學生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStudent sysStudent)
    {
        return toAjax(sysStudentService.insertSysStudent(sysStudent));
    }

    /**
     * 修改學生
     */
    @PreAuthorize("@ss.hasPermi('system:student:edit')")
    @Log(title = "學生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStudent sysStudent)
    {
        return toAjax(sysStudentService.updateSysStudent(sysStudent));
    }

    /**
     * 删除學生
     */
    @PreAuthorize("@ss.hasPermi('system:student:remove')")
    @Log(title = "學生", businessType = BusinessType.DELETE)
    @DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(sysStudentService.deleteSysStudentByIds(studentIds));
    }




//获取模板
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
//        获取学生表模板
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
        return util.importTemplateExcel("学生数据");
    }

//导入数据
    @Log(title = "学生管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:student:import')")    //@PreAuthiorize 控制一个方法是否被调用
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
//
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
//        导入表格数据
        List<SysStudent> studentList = util.importExcel(file.getInputStream());
//        更新表格数据
        String message = sysStudentService.importStudent(studentList, updateSupport);
        return AjaxResult.success(message);
    }
}
