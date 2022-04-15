package com.rebecana.blog.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rebecana.blog.dao.dos.Archives;
import com.rebecana.blog.dao.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdy
 * @since 2022-03-23
 */
@Component
public interface ArticleMapper extends BaseMapper<Article> {
    List<Archives> listArchives();

    IPage<Article> listArticle(Page<Article> page, Long categoryId, Long tagId, String year, String month);


}
