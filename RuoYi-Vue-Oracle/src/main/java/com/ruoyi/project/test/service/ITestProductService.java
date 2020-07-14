package com.ruoyi.project.test.service;

import java.util.List;
import com.ruoyi.project.test.domain.TestProduct;

/**
 * 产品管理Service接口
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
public interface ITestProductService 
{
    /**
     * 查询产品管理
     * 
     * @param productId 产品管理ID
     * @return 产品管理
     */
    public TestProduct selectTestProductById(Long productId);

    /**
     * 查询产品管理列表
     * 
     * @param testProduct 产品管理
     * @return 产品管理集合
     */
    public List<TestProduct> selectTestProductList(TestProduct testProduct);

    /**
     * 新增产品管理
     * 
     * @param testProduct 产品管理
     * @return 结果
     */
    public int insertTestProduct(TestProduct testProduct);

    /**
     * 修改产品管理
     * 
     * @param testProduct 产品管理
     * @return 结果
     */
    public int updateTestProduct(TestProduct testProduct);

    /**
     * 批量删除产品管理
     * 
     * @param productIds 需要删除的产品管理ID
     * @return 结果
     */
    public int deleteTestProductByIds(Long[] productIds);

    /**
     * 删除产品管理信息
     * 
     * @param productId 产品管理ID
     * @return 结果
     */
    public int deleteTestProductById(Long productId);
}
