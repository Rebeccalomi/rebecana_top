package com.rebecana.blog.admin.service;

import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
public interface ArticleTagService extends IService<ArticleTag> {
    public void deletebytag(Long id);

    Result update(ArticleTag articleTag);

    Result add(ArticleTag articleTag);

    Result listArticleTag(PageParam pageParam);

    Result delete(Long id);
}
