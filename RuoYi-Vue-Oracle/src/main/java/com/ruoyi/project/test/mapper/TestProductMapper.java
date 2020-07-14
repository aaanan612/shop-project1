package com.ruoyi.project.test.mapper;

import java.util.List;
import com.ruoyi.project.test.domain.TestProduct;

/**
 * 产品管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
public interface TestProductMapper 
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
     * 删除产品管理
     * 
     * @param productId 产品管理ID
     * @return 结果
     */
    public int deleteTestProductById(Long productId);

    /**
     * 批量删除产品管理
     * 
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTestProductByIds(Long[] productIds);
}
