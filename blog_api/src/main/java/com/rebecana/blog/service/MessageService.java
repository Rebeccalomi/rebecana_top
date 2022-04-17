package com.rebecana.blog.service;

import com.rebecana.blog.vo.MessageVo;
import com.rebecana.blog.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-15
 */
public interface MessageService{

    Result find();

    Result publish(MessageVo messageVo);
}
