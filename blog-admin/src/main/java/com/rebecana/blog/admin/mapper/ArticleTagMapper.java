package com.rebecana.blog.admin.mapper;

import com.rebecana.blog.admin.pojo.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
@Component
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    @Delete("DELETE FROM ms_article_tag WHERE ms_article_tag.id in(SELECT id FROM(SELECT DISTINCT ms_article_tag.id FROM ms_article,ms_category,ms_article_tag WHERE ms_article.category_id = #{Id} AND ms_article.id = ms_article_tag.article_id)as tmp)")
    void deletebycategory(Long Id);


}
