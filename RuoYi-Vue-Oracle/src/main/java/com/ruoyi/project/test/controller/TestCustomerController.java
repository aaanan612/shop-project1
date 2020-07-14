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
import com.ruoyi.project.test.domain.TestCustomer;
import com.ruoyi.project.test.service.ITestCustomerService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 客户管理Controller
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
@RestController
@RequestMapping("/test/customer")
public class TestCustomerController extends BaseController
{
    @Autowired
    private ITestCustomerService testCustomerService;

    /**
     * 查询客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('test:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestCustomer testCustomer)
    {
        startPage();
        List<TestCustomer> list = testCustomerService.selectTestCustomerList(testCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('test:customer:export')")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TestCustomer testCustomer)
    {
        List<TestCustomer> list = testCustomerService.selectTestCustomerList(testCustomer);
        ExcelUtil<TestCustomer> util = new ExcelUtil<TestCustomer>(TestCustomer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 获取客户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('test:customer:query')")
    @GetMapping(value = "/{customerId}")
    public AjaxResult getInfo(@PathVariable("customerId") Long customerId)
    {
        return AjaxResult.success(testCustomerService.selectTestCustomerById(customerId));
    }

    /**
     * 新增客户管理
     */
    @PreAuthorize("@ss.hasPermi('test:customer:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestCustomer testCustomer)
    {
        return toAjax(testCustomerService.insertTestCustomer(testCustomer));
    }

    /**
     * 修改客户管理
     */
    @PreAuthorize("@ss.hasPermi('test:customer:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestCustomer testCustomer)
    {
        return toAjax(testCustomerService.updateTestCustomer(testCustomer));
    }

    /**
     * 删除客户管理
     */
    @PreAuthorize("@ss.hasPermi('test:customer:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable Long[] customerIds)
    {
        return toAjax(testCustomerService.deleteTestCustomerByIds(customerIds));
    }
}
