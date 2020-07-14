package com.ruoyi.project.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.Message;
import com.ruoyi.project.system.service.IMessageService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 信息Controller
 * 
 * @author ruoyi
 * @date 2020-07-09
 */
@RestController
@RequestMapping("/system/message")
public class MessageController extends BaseController
{
    @Autowired
    private IMessageService messageService;

    /**
     * 查询信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(Message message)
    {
        startPage();
        List<Message> list = messageService.selectMessageList(message);
        return getDataTable(list);
    }

    /**
     * 导出信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:message:export')")
    @Log(title = "信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Message message)
    {
        List<Message> list = messageService.selectMessageList(message);
        ExcelUtil<Message> util = new ExcelUtil<Message>(Message.class);
        return util.exportExcel(list, "message");
    }

    /**
     * 获取信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:message:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(messageService.selectMessageById(id));
    }

    /**
     * 新增信息
     */
    @PreAuthorize("@ss.hasPermi('system:message:add')")
    @Log(title = "信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Message message)
    {
        System.out.println(message);
        return toAjax(messageService.insertMessage(message));
    }

    /**
     * 修改信息
     */
    @PreAuthorize("@ss.hasPermi('system:message:edit')")
    @Log(title = "信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Message message)
    {
        return toAjax(messageService.updateMessage(message));
    }

    /**
     * 删除信息
     */
    @PreAuthorize("@ss.hasPermi('system:message:remove')")
    @Log(title = "信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(messageService.deleteMessageByIds(ids));
    }
}
