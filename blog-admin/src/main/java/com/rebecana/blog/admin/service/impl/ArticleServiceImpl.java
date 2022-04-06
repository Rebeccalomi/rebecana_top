package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rebecana.blog.admin.mapper.ArticleBodyMapper;
import com.rebecana.blog.admin.mapper.ArticleTagMapper;
import com.rebecana.blog.admin.mapper.CommentMapper;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.mapper.ArticleMapper;
import com.rebecana.blog.admin.pojo.ArticleTag;
import com.rebecana.blog.admin.pojo.Tag;
import com.rebecana.blog.admin.service.ArticleBodyService;
import com.rebecana.blog.admin.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleBodyService articleBodyService;

    @Autowired
    private CommentService commentService;



    @Override
    public void deletebycategory(Long id) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getCategoryId,id);
        this.articleMapper.delete(queryWrapper);
    }
}
