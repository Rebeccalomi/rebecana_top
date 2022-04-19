package com.rebecana.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rebecana.blog.dao.mapper.MessageMapper;
import com.rebecana.blog.dao.pojo.Message;
import com.rebecana.blog.service.MessageService;
import com.rebecana.blog.vo.MessageVo;
import com.rebecana.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-04-15
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Result find() {
        LambdaQueryWrapper<Message> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Message::getTime);
        List<Message> messageList=messageMapper.selectList(queryWrapper);
        List<MessageVo> messageVoList=new ArrayList<>();
        for(Message message:messageList){
            MessageVo messageVo=new MessageVo();
            messageVo.setNickname(message.getNickname());
            messageVo.setMsg(message.getMsg());
            messageVo.setAvatar(message.getAvatar());
            messageVo.setTime(message.getTime());
            messageVoList.add(messageVo);
        }
        return Result.success(messageVoList);
    }

    @Override
    public Result publish(MessageVo messageVo) {
        Message message=new Message();
        message.setAvatar(messageVo.getAvatar());
        message.setNickname(messageVo.getNickname());
        message.setMsg(messageVo.getMsg());
        message.setTime(messageVo.getTime());
        messageMapper.insert(message);
        return Result.success(null);
    }
}
