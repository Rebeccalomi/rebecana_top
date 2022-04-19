package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.pojo.ArticleBody;
import com.rebecana.blog.admin.mapper.ArticleBodyMapper;
import com.rebecana.blog.admin.pojo.Comment;
import com.rebecana.blog.admin.service.ArticleBodyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ArticleBodyServiceImpl extends ServiceImpl<ArticleBodyMapper, ArticleBody> implements ArticleBodyService {

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    @Override
    public void deletebycategory(Long id) {
        articleBodyMapper.deletebycategory(id);
    }
}
