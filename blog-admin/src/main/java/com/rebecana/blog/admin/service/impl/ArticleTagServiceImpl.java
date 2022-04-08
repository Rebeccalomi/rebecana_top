package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.pojo.ArticleTag;
import com.rebecana.blog.admin.mapper.ArticleTagMapper;
import com.rebecana.blog.admin.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public void deletebytag(Long id) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getTagId,id);
        this.articleTagMapper.delete(queryWrapper);
    }

    @Override
    public Result update(ArticleTag articleTag) {
        this.articleTagMapper.updateById(articleTag);
        return Result.success(null);
    }

    @Override
    public Result add(ArticleTag articleTag) {
        this.articleTagMapper.insert(articleTag);
        return Result.success(null);
    }

    @Override
    public Result listArticleTag(PageParam pageParam) {
        Page<ArticleTag> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(ArticleTag::getId,pageParam.getQueryString());
        }
        Page<ArticleTag> articleTagPage = articleTagMapper.selectPage(page, queryWrapper);
        PageResult<ArticleTag> pageResult = new PageResult<>();
        pageResult.setList(articleTagPage.getRecords());
        pageResult.setTotal(articleTagPage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result delete(Long id) {
        this.articleTagMapper.deleteById(id);
        return Result.success(null);
    }
}
