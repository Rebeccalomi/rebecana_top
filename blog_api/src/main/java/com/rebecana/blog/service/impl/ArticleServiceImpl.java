package com.rebecana.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rebecana.blog.dao.dos.Archives;
import com.rebecana.blog.dao.mapper.ArticleBodyMapper;
import com.rebecana.blog.dao.pojo.Article;
import com.rebecana.blog.dao.mapper.ArticleMapper;
import com.rebecana.blog.dao.pojo.ArticleBody;
import com.rebecana.blog.dao.pojo.SysUser;
import com.rebecana.blog.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.vo.*;
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
            articleVoList.add(copy(record,isTag,isAuthor,false,false));
        }
        return articleVoList;
    }
    private  List<ArticleVo> copyList(List<Article> records,boolean isTag,boolean isAuthor,boolean isBody,boolean isCate){
        List<ArticleVo> articleVoList=new ArrayList<>();
        for(Article record : records){
            articleVoList.add(copy(record,isTag,isAuthor,isBody,isCate));
        }
        return articleVoList;
    }

    private ArticleVo copy(Article article ,boolean isTag,boolean isAuthor,boolean isBody,boolean isCate){
        ArticleVo articleVo=new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        //并不是所有的接口,都需要标签和作者信息
        if(isTag){
            Long articleId=article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if(isAuthor){
//            Long authorId=article.getAuthorId();
//            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
            Long authorId = article.getAuthorId();
            SysUser sysUser = sysUserService.findUserById(authorId);
            UserVo userVo = new UserVo();
            userVo.setAvatar(sysUser.getAvatar());
            userVo.setId(sysUser.getId().toString());
            userVo.setNickname(sysUser.getNickname());
            articleVo.setAuthor(userVo);
        }
        if(isBody){
            Long articleId = article.getId();
            articleVo.setBody(findArticleBodyById(articleId));
        }
        if (isCate){
            Long categoryId = article.getCategoryId();
            articleVo.setCategory(categoryService.findCategoryById(categoryId));
        }
        return articleVo;
    }
    @Autowired
    private ThreadService threadService;

    @Override
    public Result findArticleById(Long articleId) {
        /**
         * 1。根据id查询文章信息
         * 2。根据bodyid和categoryid做关联查询
         */
        Article article=articleMapper.selectById(articleId);
        ArticleVo articleVo=copy(article,true,true,true,true);
        //查看完文章之后新增阅读数有没有问题？
        //查看完之后，本应该直接返回数据了，这时候做了一个更新操作，更新时加写锁，阻塞其他的的读操作，性能会比较低
        //更新 增加了此次接口的耗时 如果一旦更新出问题 不能影响查看文章的操作
        //线程池 可以吧更新操作扔到线程池执行，和主线程就不相关了
        threadService.updateArticleViewCount(articleMapper,article);
        return Result.success(articleVo);
    }

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    private ArticleBodyVo findArticleBodyById(Long articleId) {
        LambdaQueryWrapper<ArticleBody> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleBody::getArticleId,articleId);
        ArticleBody articleBody = articleBodyMapper.selectOne(queryWrapper);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }



    @Override
    public Result hotArticle(int limit){
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCounts);
        queryWrapper.select(Article::getId,Article::getTitle);
        queryWrapper.last("limit "+limit);
        //select id,title from article order by view_counts desc limit 5
        List<Article> articles=articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result newArticles(int limit) {
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate);
        queryWrapper.select(Article::getId,Article::getTitle);
        queryWrapper.last("limit "+limit);
        //select id,title from article order by view_counts desc limit 5
        List<Article> articles=articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result listArchives() {
        List<Archives> archiveslist=articleMapper.listArchives();
        return Result.success(archiveslist);
    }




}
