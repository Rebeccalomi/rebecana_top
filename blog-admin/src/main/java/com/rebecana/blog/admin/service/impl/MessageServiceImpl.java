package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.mapper.MessageMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Message;
import com.rebecana.blog.admin.service.MessageService;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-04-17
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Result update(Message message) {
        messageMapper.updateById(message);
        return Result.success(null);
    }

    @Override
    public Result listMessage(PageParam pageParam) {
        Page<Message> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(Message::getNickname,pageParam.getQueryString());
        }
        Page<Message> messagePage = messageMapper.selectPage(page, queryWrapper);
        PageResult<Message> pageResult = new PageResult<>();
        pageResult.setList(messagePage.getRecords());
        pageResult.setTotal(messagePage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result add(Message message) {
        messageMapper.insert(message);
        return Result.success(null);
    }

    @Override
    public Result delete(Long id) {
        messageMapper.deleteById(id);
        return Result.success(null);
    }
}
