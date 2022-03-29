package com.rebecana.blog.service;

import com.rebecana.blog.dao.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.TagVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-03-23
 */
public interface TagService extends IService<Tag> {
    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int limit);

}
