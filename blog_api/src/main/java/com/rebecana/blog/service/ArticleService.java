package com.rebecana.blog.service;

import com.rebecana.blog.dao.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.vo.Result;
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

}
