package com.ruoyi.project.test.mapper;

import java.util.List;
import com.ruoyi.project.test.domain.TestCustomer;

/**
 * 客户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
public interface TestCustomerMapper 
{
    /**
     * 查询客户管理
     * 
     * @param customerId 客户管理ID
     * @return 客户管理
     */
    public TestCustomer selectTestCustomerById(Long customerId);

    /**
     * 查询客户管理列表
     * 
     * @param testCustomer 客户管理
     * @return 客户管理集合
     */
    public List<TestCustomer> selectTestCustomerList(TestCustomer testCustomer);

    /**
     * 新增客户管理
     * 
     * @param testCustomer 客户管理
     * @return 结果
     */
    public int insertTestCustomer(TestCustomer testCustomer);

    /**
     * 修改客户管理
     * 
     * @param testCustomer 客户管理
     * @return 结果
     */
    public int updateTestCustomer(TestCustomer testCustomer);

    /**
     * 删除客户管理
     * 
     * @param customerId 客户管理ID
     * @return 结果
     */
    public int deleteTestCustomerById(Long customerId);

    /**
     * 批量删除客户管理
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTestCustomerByIds(Long[] customerIds);
}
