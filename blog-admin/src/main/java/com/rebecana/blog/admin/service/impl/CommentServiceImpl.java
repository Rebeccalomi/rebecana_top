package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.pojo.Comment;
import com.rebecana.blog.admin.mapper.CommentMapper;
import com.rebecana.blog.admin.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public Result listComment(PageParam pageParam) {
        Page<Comment> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Comment::getCreateDate);
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(Comment::getId,pageParam.getQueryString());
        }
        Page<Comment> commentPage = commentMapper.selectPage(page, queryWrapper);
        PageResult<Comment> pageResult = new PageResult<>();
        pageResult.setList(commentPage.getRecords());
        pageResult.setTotal(commentPage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result add(Comment comment) {
        this.commentMapper.insert(comment);
        return Result.success(null);
    }

    @Override
    public Result update(Comment comment) {
        this.commentMapper.updateById(comment);
        return Result.success(null);
    }

    @Override
    public Result delete(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId,id);
        this.commentMapper.delete(queryWrapper);
        this.commentMapper.deleteById(id);
        return Result.success(null);
    }
}
