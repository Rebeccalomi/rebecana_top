package com.rebecana.blog.admin.service;

import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.pojo.Tag;
import com.rebecana.blog.admin.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
public interface ArticleService extends IService<Article> {

    void deletebycategory(Long id);

    Result listArticle(PageParam pageParam);

    Result add(Article article);

    Result delete(Long id);

    Result update(Article article);
}
