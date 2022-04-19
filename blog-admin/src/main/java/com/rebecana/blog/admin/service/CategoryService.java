package com.rebecana.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rebecana.blog.admin.mapper.CategoryMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.ArticleTag;
import com.rebecana.blog.admin.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.pojo.Tag;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
@Service
public interface CategoryService extends IService<Category>{

    public Result listTag(PageParam pageParam);

    public Result add(Category category);

    public Result update(Category category);
    public Result delete(Long id);

}
