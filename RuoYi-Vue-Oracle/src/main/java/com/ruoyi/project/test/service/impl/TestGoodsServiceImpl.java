package com.ruoyi.project.test.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.test.mapper.TestGoodsMapper;
import com.ruoyi.project.test.domain.TestGoods;
import com.ruoyi.project.test.service.ITestGoodsService;

/**
 * 商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
@Service
public class TestGoodsServiceImpl implements ITestGoodsService 
{
    @Autowired
    private TestGoodsMapper testGoodsMapper;

    /**
     * 查询商品
     * 
     * @param goodsId 商品ID
     * @return 商品
     */
    @Override
    public TestGoods selectTestGoodsById(Long goodsId)
    {
        return testGoodsMapper.selectTestGoodsById(goodsId);
    }

    /**
     * 查询商品列表
     * 
     * @param testGoods 商品
     * @return 商品
     */
    @Override
    public List<TestGoods> selectTestGoodsList(TestGoods testGoods)
    {
        return testGoodsMapper.selectTestGoodsList(testGoods);
    }

    /**
     * 新增商品
     * 
     * @param testGoods 商品
     * @return 结果
     */
    @Override
    public int insertTestGoods(TestGoods testGoods)
    {
        return testGoodsMapper.insertTestGoods(testGoods);
    }

    /**
     * 修改商品
     * 
     * @param testGoods 商品
     * @return 结果
     */
    @Override
    public int updateTestGoods(TestGoods testGoods)
    {
        return testGoodsMapper.updateTestGoods(testGoods);
    }

    /**
     * 批量删除商品
     * 
     * @param goodsIds 需要删除的商品ID
     * @return 结果
     */
    @Override
    public int deleteTestGoodsByIds(Long[] goodsIds)
    {
        return testGoodsMapper.deleteTestGoodsByIds(goodsIds);
    }

    /**
     * 删除商品信息
     * 
     * @param goodsId 商品ID
     * @return 结果
     */
    @Override
    public int deleteTestGoodsById(Long goodsId)
    {
        return testGoodsMapper.deleteTestGoodsById(goodsId);
    }
}
