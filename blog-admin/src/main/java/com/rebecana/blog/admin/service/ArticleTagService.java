package com.rebecana.blog.admin.service;

import com.rebecana.blog.admin.pojo.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
