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
import com.ruoyi.project.test.domain.TestGoods;
import com.ruoyi.project.test.service.ITestGoodsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 商品Controller
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
@RestController
@RequestMapping("/test/goods")
public class TestGoodsController extends BaseController
{
    @Autowired
    private ITestGoodsService testGoodsService;

    /**
     * 查询商品列表
     */
    @PreAuthorize("@ss.hasPermi('test:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestGoods testGoods)
    {
        startPage();
        List<TestGoods> list = testGoodsService.selectTestGoodsList(testGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize("@ss.hasPermi('test:goods:export')")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TestGoods testGoods)
    {
        List<TestGoods> list = testGoodsService.selectTestGoodsList(testGoods);
        ExcelUtil<TestGoods> util = new ExcelUtil<TestGoods>(TestGoods.class);
        return util.exportExcel(list, "goods");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('test:goods:query')")
    @GetMapping(value = "/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId)
    {
        return AjaxResult.success(testGoodsService.selectTestGoodsById(goodsId));
    }

    /**
     * 新增商品
     */
    @PreAuthorize("@ss.hasPermi('test:goods:add')")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestGoods testGoods)
    {
        return toAjax(testGoodsService.insertTestGoods(testGoods));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.hasPermi('test:goods:edit')")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestGoods testGoods)
    {
        return toAjax(testGoodsService.updateTestGoods(testGoods));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.hasPermi('test:goods:remove')")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds)
    {
        return toAjax(testGoodsService.deleteTestGoodsByIds(goodsIds));
    }
}
