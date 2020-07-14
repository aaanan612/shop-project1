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
import com.ruoyi.project.test.domain.TestProduct;
import com.ruoyi.project.test.service.ITestProductService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品管理Controller
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
@RestController
@RequestMapping("/test/product")
public class TestProductController extends BaseController
{
    @Autowired
    private ITestProductService testProductService;

    /**
     * 查询产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('test:product:list')")
    @GetMapping("/list")
    public AjaxResult list(TestProduct testProduct)
    {
        List<TestProduct> list = testProductService.selectTestProductList(testProduct);
        return AjaxResult.success(list);
    }

    /**
     * 导出产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('test:product:export')")
    @Log(title = "产品管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TestProduct testProduct)
    {
        List<TestProduct> list = testProductService.selectTestProductList(testProduct);
        ExcelUtil<TestProduct> util = new ExcelUtil<TestProduct>(TestProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 获取产品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('test:product:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId)
    {
        return AjaxResult.success(testProductService.selectTestProductById(productId));
    }

    /**
     * 新增产品管理
     */
    @PreAuthorize("@ss.hasPermi('test:product:add')")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestProduct testProduct)
    {
        return toAjax(testProductService.insertTestProduct(testProduct));
    }

    /**
     * 修改产品管理
     */
    @PreAuthorize("@ss.hasPermi('test:product:edit')")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestProduct testProduct)
    {
        return toAjax(testProductService.updateTestProduct(testProduct));
    }

    /**
     * 删除产品管理
     */
    @PreAuthorize("@ss.hasPermi('test:product:remove')")
    @Log(title = "产品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds)
    {
        return toAjax(testProductService.deleteTestProductByIds(productIds));
    }
}
