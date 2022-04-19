package com.rebecana.blog.admin.service;

import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-17
 */
public interface MessageService extends IService<Message> {

    Result update(Message message);

    Result listMessage(PageParam pageParam);

    Result add(Message message);

    Result delete(Long id);
}
