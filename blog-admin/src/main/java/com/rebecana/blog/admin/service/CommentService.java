package com.rebecana.blog.admin.service;

import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.vo.Result;

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

    Result listComment(PageParam pageParam);

    Result add(Comment comment);

    Result update(Comment comment);

    Result delete(Long id);
}
