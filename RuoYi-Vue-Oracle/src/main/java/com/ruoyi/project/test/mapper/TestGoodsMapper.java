package com.ruoyi.project.test.mapper;

import java.util.List;
import com.ruoyi.project.test.domain.TestGoods;

/**
 * 商品Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
public interface TestGoodsMapper 
{
    /**
     * 查询商品
     * 
     * @param goodsId 商品ID
     * @return 商品
     */
    public TestGoods selectTestGoodsById(Long goodsId);

    /**
     * 查询商品列表
     * 
     * @param testGoods 商品
     * @return 商品集合
     */
    public List<TestGoods> selectTestGoodsList(TestGoods testGoods);

    /**
     * 新增商品
     * 
     * @param testGoods 商品
     * @return 结果
     */
    public int insertTestGoods(TestGoods testGoods);

    /**
     * 修改商品
     * 
     * @param testGoods 商品
     * @return 结果
     */
    public int updateTestGoods(TestGoods testGoods);

    /**
     * 删除商品
     * 
     * @param goodsId 商品ID
     * @return 结果
     */
    public int deleteTestGoodsById(Long goodsId);

    /**
     * 批量删除商品
     * 
     * @param goodsIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTestGoodsByIds(Long[] goodsIds);
}
