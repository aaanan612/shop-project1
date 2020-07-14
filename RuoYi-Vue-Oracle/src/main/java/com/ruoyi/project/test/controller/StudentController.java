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
import com.ruoyi.project.test.domain.Student;
import com.ruoyi.project.test.service.IStudentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 学生Controller
 * 
 * @author ruoyi
 * @date 2020-07-03
 */
@RestController
@RequestMapping("/test/student")
public class StudentController extends BaseController
{
    @Autowired
    private IStudentService studentService;

    /**
     * 查询学生列表
     */
    @PreAuthorize("@ss.hasPermi('test:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(Student student)
    {
        startPage();
        List<Student> list = studentService.selectStudentList(student);
        return getDataTable(list);
    }

    /**
     * 导出学生列表
     */
    @PreAuthorize("@ss.hasPermi('test:student:export')")
    @Log(title = "学生", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Student student)
    {
        List<Student> list = studentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.exportExcel(list, "student");
    }

    /**
     * 获取学生详细信息
     */
    @PreAuthorize("@ss.hasPermi('test:student:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(studentService.selectStudentById(id));
    }

    /**
     * 新增学生
     */
    @PreAuthorize("@ss.hasPermi('test:student:add')")
    @Log(title = "学生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Student student)
    {
        return toAjax(studentService.insertStudent(student));
    }

    /**
     * 修改学生
     */
    @PreAuthorize("@ss.hasPermi('test:student:edit')")
    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Student student)
    {
        return toAjax(studentService.updateStudent(student));
    }

    /**
     * 删除学生
     */
    @PreAuthorize("@ss.hasPermi('test:student:remove')")
    @Log(title = "学生", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(studentService.deleteStudentByIds(ids));
    }



    //获取模板
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
//        获取学生表模板
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.importTemplateExcel("学生数据");
    }

    //导入数据
    @Log(title = "学生管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('test:student:import')")    //@PreAuthiorize 控制一个方法是否被调用
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
//
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
//        导入表格数据
        List<Student> studentList = util.importExcel(file.getInputStream());
//        更新表格数据
        String message = studentService.importStudent(studentList, updateSupport);
        return AjaxResult.success(message);
    }
}
