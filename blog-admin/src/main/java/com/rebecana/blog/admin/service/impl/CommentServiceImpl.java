package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.pojo.Comment;
import com.rebecana.blog.admin.mapper.CommentMapper;
import com.rebecana.blog.admin.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public void deletebycategory(Long id) {
        commentMapper.deletebycategory(id);
    }
}
