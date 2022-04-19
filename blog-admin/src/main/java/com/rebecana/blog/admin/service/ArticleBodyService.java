package com.rebecana.blog.admin.service;

import com.rebecana.blog.admin.mapper.ArticleBodyMapper;
import com.rebecana.blog.admin.pojo.ArticleBody;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */

public interface ArticleBodyService extends IService<ArticleBody> {

    void deletebycategory(Long id);
}
