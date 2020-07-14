package com.ruoyi.project.test.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.test.mapper.TestProductMapper;
import com.ruoyi.project.test.domain.TestProduct;
import com.ruoyi.project.test.service.ITestProductService;

/**
 * 产品管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
@Service
public class TestProductServiceImpl implements ITestProductService 
{
    @Autowired
    private TestProductMapper testProductMapper;

    /**
     * 查询产品管理
     * 
     * @param productId 产品管理ID
     * @return 产品管理
     */
    @Override
    public TestProduct selectTestProductById(Long productId)
    {
        return testProductMapper.selectTestProductById(productId);
    }

    /**
     * 查询产品管理列表
     * 
     * @param testProduct 产品管理
     * @return 产品管理
     */
    @Override
    public List<TestProduct> selectTestProductList(TestProduct testProduct)
    {
        return testProductMapper.selectTestProductList(testProduct);
    }

    /**
     * 新增产品管理
     * 
     * @param testProduct 产品管理
     * @return 结果
     */
    @Override
    public int insertTestProduct(TestProduct testProduct)
    {
        return testProductMapper.insertTestProduct(testProduct);
    }

    /**
     * 修改产品管理
     * 
     * @param testProduct 产品管理
     * @return 结果
     */
    @Override
    public int updateTestProduct(TestProduct testProduct)
    {
        return testProductMapper.updateTestProduct(testProduct);
    }

    /**
     * 批量删除产品管理
     * 
     * @param productIds 需要删除的产品管理ID
     * @return 结果
     */
    @Override
    public int deleteTestProductByIds(Long[] productIds)
    {
        return testProductMapper.deleteTestProductByIds(productIds);
    }

    /**
     * 删除产品管理信息
     * 
     * @param productId 产品管理ID
     * @return 结果
     */
    @Override
    public int deleteTestProductById(Long productId)
    {
        return testProductMapper.deleteTestProductById(productId);
    }
}
