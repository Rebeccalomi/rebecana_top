package com.rebecana.blog.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.mapper.*;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.pojo.SysUser;
import com.rebecana.blog.admin.pojo.Tag;
import com.rebecana.blog.admin.service.ArticleService;
import com.rebecana.blog.admin.service.CategoryService;
import com.rebecana.blog.admin.service.SysUserService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ArticleService articleService;

    public Result listTag(PageParam pageParam) {
        Page<Category> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(Category::getCategoryName,pageParam.getQueryString());
        }
        Page<Category> categoryPage = categoryMapper.selectPage(page, queryWrapper);
        PageResult<Category> pageResult = new PageResult<>();
        pageResult.setList(categoryPage.getRecords());
        pageResult.setTotal(categoryPage.getTotal());
        return Result.success(pageResult);
    }

    public Result add(Category category) {
        this.categoryMapper.insert(category);
        return Result.success(null);
    }

    public Result update(Category category) {
        this.categoryMapper.updateById(category);
        return Result.success(null);
    }

    public Result delete(Long id) {
        articleBodyMapper.deletebycategory(id);
        commentMapper.deletebycategory(id);
        articleTagMapper.deletebycategory(id);
        this.categoryMapper.deleteById(id);
        articleService.deletebycategory(id);
        return Result.success(null);
    }

}
