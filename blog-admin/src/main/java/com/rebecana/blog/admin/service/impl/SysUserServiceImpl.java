package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rebecana.blog.admin.mapper.CommentMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.*;
import com.rebecana.blog.admin.mapper.SysUserMapper;
import com.rebecana.blog.admin.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result listArticle(PageParam pageParam) {
        Page<SysUser> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(SysUser::getCreateDate);
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(SysUser::getId,pageParam.getQueryString());
        }
        Page<SysUser> sysUserPage = sysUserMapper.selectPage(page, queryWrapper);
        PageResult<SysUser> pageResult = new PageResult<>();
        pageResult.setList(sysUserPage.getRecords());
        pageResult.setTotal(sysUserPage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result add(SysUser sysUser) {
        this.sysUserMapper.insert(sysUser);
        return Result.success(null);
    }

    @Override
    public Result update(SysUser sysUser) {
        this.sysUserMapper.updateById(sysUser);
        return Result.success(null);
    }

    @Override
    public Result delete(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getAuthorId,id);
        this.commentMapper.delete(queryWrapper);
        this.sysUserMapper.deleteById(id);
        return Result.success(null);
    }
}
