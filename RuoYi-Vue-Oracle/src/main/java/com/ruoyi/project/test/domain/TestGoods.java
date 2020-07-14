package com.ruoyi.project.test.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 商品对象 test_goods
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
public class TestGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long goodsId;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 商品重量 */
    @Excel(name = "商品重量")
    private Long weight;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private Double price;

    /** 商品种类 */
    @Excel(name = "商品种类")
    private String type;

    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Double getPrice()
    {
        return price;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("customerId", getCustomerId())
            .append("name", getName())
            .append("weight", getWeight())
            .append("price", getPrice())
            .append("type", getType())
            .toString();
    }
}
