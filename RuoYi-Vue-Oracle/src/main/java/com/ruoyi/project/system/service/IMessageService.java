package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.Message;

/**
 * 信息Service接口
 * 
 * @author ruoyi
 * @date 2020-07-09
 */
public interface IMessageService 
{
    /**
     * 查询信息
     * 
     * @param id 信息ID
     * @return 信息
     */
    public Message selectMessageById(Long id);

    /**
     * 查询信息列表
     * 
     * @param message 信息
     * @return 信息集合
     */
    public List<Message> selectMessageList(Message message);

    /**
     * 新增信息
     * 
     * @param message 信息
     * @return 结果
     */
    public int insertMessage(Message message);

    /**
     * 修改信息
     * 
     * @param message 信息
     * @return 结果
     */
    public int updateMessage(Message message);

    /**
     * 批量删除信息
     * 
     * @param ids 需要删除的信息ID
     * @return 结果
     */
    public int deleteMessageByIds(Long[] ids);

    /**
     * 删除信息信息
     * 
     * @param id 信息ID
     * @return 结果
     */
    public int deleteMessageById(Long id);
}
