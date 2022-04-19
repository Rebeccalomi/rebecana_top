package com.rebecana.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.vo.Echart1Vo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
@Component
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("SELECT title,comment_counts,view_counts FROM ms_article")
    List<Echart1Vo> selectechart1();
}
