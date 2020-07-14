package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.MessageMapper;
import com.ruoyi.project.system.domain.Message;
import com.ruoyi.project.system.service.IMessageService;

/**
 * 信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-09
 */
@Service
public class MessageServiceImpl implements IMessageService 
{
    @Autowired
    private MessageMapper messageMapper;

    /**
     * 查询信息
     * 
     * @param id 信息ID
     * @return 信息
     */
    @Override
    public Message selectMessageById(Long id)
    {
        return messageMapper.selectMessageById(id);
    }

    /**
     * 查询信息列表
     * 
     * @param message 信息
     * @return 信息
     */
    @Override
    public List<Message> selectMessageList(Message message)
    {
        return messageMapper.selectMessageList(message);
    }

    /**
     * 新增信息
     * 
     * @param message 信息
     * @return 结果
     */
    @Override
    public int insertMessage(Message message)
    {
        return messageMapper.insertMessage(message);
    }

    /**
     * 修改信息
     * 
     * @param message 信息
     * @return 结果
     */
    @Override
    public int updateMessage(Message message)
    {
        return messageMapper.updateMessage(message);
    }

    /**
     * 批量删除信息
     * 
     * @param ids 需要删除的信息ID
     * @return 结果
     */
    @Override
    public int deleteMessageByIds(Long[] ids)
    {
        return messageMapper.deleteMessageByIds(ids);
    }

    /**
     * 删除信息信息
     * 
     * @param id 信息ID
     * @return 结果
     */
    @Override
    public int deleteMessageById(Long id)
    {
        return messageMapper.deleteMessageById(id);
    }
}
