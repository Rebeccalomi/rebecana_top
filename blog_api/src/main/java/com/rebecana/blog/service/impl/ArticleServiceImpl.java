package com.rebecana.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rebecana.blog.dao.pojo.Article;
import com.rebecana.blog.dao.mapper.ArticleMapper;
import com.rebecana.blog.dao.pojo.SysUser;
import com.rebecana.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.service.SysUserService;
import com.rebecana.blog.service.TagService;
import com.rebecana.blog.vo.ArticleVo;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-03-23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result listArticle(PageParams pageParams){
        Page<Article> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        //order by create_date desc
        //是否置顶进行排序
        queryWrapper.orderByDesc(Article::getWeight,Article::getCreateDate);//倒序排列
        Page<Article> articlePage=articleMapper.selectPage(page,queryWrapper);
        List<Article> records = articlePage.getRecords();
        List<ArticleVo> articleVoList = copyList(records,true,true);
        return Result.success(articleVoList);
    }

    private  List<ArticleVo> copyList(List<Article> records,boolean isTag,boolean isAuthor){
        List<ArticleVo> articleVoList=new ArrayList<>();
        for(Article record : records){
            articleVoList.add(copy(record,true,true));
        }
        return articleVoList;
    }
    private ArticleVo copy(Article article ,boolean isTag,boolean isAuthor){
        ArticleVo articleVo=new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        //并不是所有的接口,都需要标签和作者信息
        if(isTag){
            Long articleId=article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if(isAuthor){
            Long authorId=article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
            System.out.println(sysUserService.findUserById(authorId).getNickname());
        }

        return articleVo;
    }


}
