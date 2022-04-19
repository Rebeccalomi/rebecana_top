package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.mapper.SysUserMapper;
import com.rebecana.blog.admin.mapper.TagMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.SysUser;
import com.rebecana.blog.admin.pojo.Tag;
import com.rebecana.blog.admin.service.ArticleTagService;
import com.rebecana.blog.admin.service.SysUserService;
import com.rebecana.blog.admin.service.TagService;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagService articleTagService;

    public Result listTag(PageParam pageParam) {
        Page<Tag> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(Tag::getTagName,pageParam.getQueryString());
        }
        Page<Tag> tagPage = tagMapper.selectPage(page, queryWrapper);
        PageResult<Tag> pageResult = new PageResult<>();
        pageResult.setList(tagPage.getRecords());
        pageResult.setTotal(tagPage.getTotal());
        return Result.success(pageResult);
    }

    public Result add(Tag tag) {
        this.tagMapper.insert(tag);
        return Result.success(null);
    }

    public Result delete(Long id) {
        this.tagMapper.deleteById(id);
        articleTagService.deletebytag(id);
        return Result.success(null);
    }

    public Result update(Tag tag) {
        this.tagMapper.updateById(tag);
        return Result.success(null);
    }
}
