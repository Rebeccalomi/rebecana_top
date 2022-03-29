package com.rebecana.blog.dao.mapper;

import com.rebecana.blog.dao.dos.Archives;
import com.rebecana.blog.dao.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdy
 * @since 2022-03-23
 */
public interface ArticleMapper extends BaseMapper<Article> {
    List<Archives> listArchives();

}
