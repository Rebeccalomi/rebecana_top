package com.rebecana.blog.admin.mapper;

import com.rebecana.blog.admin.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rebecana.blog.admin.pojo.Permission;
import org.apache.ibatis.annotations.Delete;
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
public interface CommentMapper extends BaseMapper<Comment> {

    @Delete("DELETE FROM ms_comment WHERE ms_comment.id in(SELECT id FROM(SELECT DISTINCT ms_comment.id FROM ms_article,ms_category,ms_comment WHERE ms_article.category_id = #{Id} AND ms_article.id = ms_comment.article_id)as tmp)")
    void deletebycategory(Long Id);

}
