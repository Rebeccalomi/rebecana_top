package com.rebecana.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.dao.pojo.Article;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.Search;
import com.rebecana.blog.vo.params.ArticleParam;
import com.rebecana.blog.vo.params.PageParams;

/**
 * <p>
 *  服务类
 *
 * </p>
 *
 * @author zdy
 * @since 2022-03-23
 */

public interface ArticleService extends IService<Article> {
    /**
     * 分页查询文章列表
     * @param pageParams
     * @return
     */
    public Result listArticle(PageParams pageParams);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    public Result hotArticle(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    public Result newArticles(int limit);

    /**
     * 文章归档
     * @return
     */
    public Result listArchives();

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

    /**
     * 文章发布服务
     * @param articleParam
     * @return
     */
    Result publish(ArticleParam articleParam);

    Result updateArticle(ArticleParam articleParam);

    Result search(Search search);
}
