package com.ruoyi.project.test.service;

import java.util.List;
import com.ruoyi.project.test.domain.TestCustomer;

/**
 * 客户管理Service接口
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
public interface ITestCustomerService 
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
     * 批量删除客户管理
     * 
     * @param customerIds 需要删除的客户管理ID
     * @return 结果
     */
    public int deleteTestCustomerByIds(Long[] customerIds);

    /**
     * 删除客户管理信息
     * 
     * @param customerId 客户管理ID
     * @return 结果
     */
    public int deleteTestCustomerById(Long customerId);
}
