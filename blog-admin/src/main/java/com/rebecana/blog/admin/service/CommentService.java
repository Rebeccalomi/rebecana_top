package com.rebecana.blog.admin.service;

import com.rebecana.blog.admin.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
public interface CommentService extends IService<Comment> {

    void deletebycategory(Long id);
}
