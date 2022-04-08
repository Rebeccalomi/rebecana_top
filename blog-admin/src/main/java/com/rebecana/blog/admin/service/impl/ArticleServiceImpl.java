package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.mapper.ArticleBodyMapper;
import com.rebecana.blog.admin.mapper.ArticleMapper;
import com.rebecana.blog.admin.mapper.ArticleTagMapper;
import com.rebecana.blog.admin.mapper.CommentMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.pojo.ArticleBody;
import com.rebecana.blog.admin.pojo.ArticleTag;
import com.rebecana.blog.admin.pojo.Comment;
import com.rebecana.blog.admin.service.ArticleService;
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
 * @since 2022-04-05
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private CommentMapper commentMapper;



    @Override
    public void deletebycategory(Long id) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getCategoryId,id);
        this.articleMapper.delete(queryWrapper);
    }

    @Override
    public Result listArticle(PageParam pageParam) {
        Page<Article> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate);
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(Article::getId,pageParam.getQueryString());
        }
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        PageResult<Article> pageResult = new PageResult<>();
        pageResult.setList(articlePage.getRecords());
        pageResult.setTotal(articlePage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result add(Article article) {
        this.articleMapper.insert(article);
        return Result.success(null);
    }

    @Override
    public Result update(Article article) {
        this.articleMapper.updateById(article);
        return Result.success(null);
    }

    @Override
    public Result delete(Long id) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId,id);
        articleTagMapper.delete(queryWrapper);
        LambdaQueryWrapper<Comment> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Comment::getArticleId,id);
        commentMapper.delete(queryWrapper1);
        LambdaQueryWrapper<ArticleBody> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(ArticleBody::getArticleId,id);
        articleBodyMapper.delete(queryWrapper2);
        this.articleMapper.deleteById(id);
        return Result.success(null);
    }

}
