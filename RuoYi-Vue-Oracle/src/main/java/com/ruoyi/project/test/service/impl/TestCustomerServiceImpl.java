package com.ruoyi.project.test.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.test.mapper.TestCustomerMapper;
import com.ruoyi.project.test.domain.TestCustomer;
import com.ruoyi.project.test.service.ITestCustomerService;

/**
 * 客户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-06
 */
@Service
public class TestCustomerServiceImpl implements ITestCustomerService 
{
    @Autowired
    private TestCustomerMapper testCustomerMapper;

    /**
     * 查询客户管理
     * 
     * @param customerId 客户管理ID
     * @return 客户管理
     */
    @Override
    public TestCustomer selectTestCustomerById(Long customerId)
    {
        return testCustomerMapper.selectTestCustomerById(customerId);
    }

    /**
     * 查询客户管理列表
     * 
     * @param testCustomer 客户管理
     * @return 客户管理
     */
    @Override
    public List<TestCustomer> selectTestCustomerList(TestCustomer testCustomer)
    {
        return testCustomerMapper.selectTestCustomerList(testCustomer);
    }

    /**
     * 新增客户管理
     * 
     * @param testCustomer 客户管理
     * @return 结果
     */
    @Override
    public int insertTestCustomer(TestCustomer testCustomer)
    {
        return testCustomerMapper.insertTestCustomer(testCustomer);
    }

    /**
     * 修改客户管理
     * 
     * @param testCustomer 客户管理
     * @return 结果
     */
    @Override
    public int updateTestCustomer(TestCustomer testCustomer)
    {
        return testCustomerMapper.updateTestCustomer(testCustomer);
    }

    /**
     * 批量删除客户管理
     * 
     * @param customerIds 需要删除的客户管理ID
     * @return 结果
     */
    @Override
    public int deleteTestCustomerByIds(Long[] customerIds)
    {
        return testCustomerMapper.deleteTestCustomerByIds(customerIds);
    }

    /**
     * 删除客户管理信息
     * 
     * @param customerId 客户管理ID
     * @return 结果
     */
    @Override
    public int deleteTestCustomerById(Long customerId)
    {
        return testCustomerMapper.deleteTestCustomerById(customerId);
    }
}
