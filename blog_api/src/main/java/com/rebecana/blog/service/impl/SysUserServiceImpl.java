package com.rebecana.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rebecana.blog.dao.mapper.SysUserMapper;
import com.rebecana.blog.dao.pojo.Article;
import com.rebecana.blog.dao.pojo.SysUser;
import com.rebecana.blog.service.ArticleService;
import com.rebecana.blog.service.LoginService;
import com.rebecana.blog.service.SysUserService;
import com.rebecana.blog.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LoginService loginService;


    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setNickname("rebecana");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(SysUser sysUser) {
        //保存用户 id自动生成
        //默认生成对id是分布式id，雪花算法
        //mybatis-plus
        sysUserMapper.insert(sysUser);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1. token合法性校验
         *    是否为空，解析是否成功 redis是否存在
         * 2. 如果校验失败 返回错误
         * 3. 如果成功，返回对应的结果 LoginUserVo
         */
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null){
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(),ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(sysUser.getId().toString());
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setAccount(sysUser.getAccount());
        return Result.success(loginUserVo);
    }

    @Override
    public UserVo findUserVoById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setNickname("rebecana");
        }
        UserVo userVo = new UserVo();
        userVo.setAvatar(sysUser.getAvatar());
        userVo.setNickname(sysUser.getNickname());
        userVo.setId(sysUser.getId().toString());
        return userVo;
    }

    @Override
    public Result findMe() {
        MyVo me=new MyVo();
        SysUser sysUser = sysUserMapper.selectById(1);
        me.setAvatar(sysUser.getAvatar());
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("SUM(ms_article.comment_counts) as commentcounts");
        Map<String, Object> map=articleService.getMap(queryWrapper);
        me.setCommentCounts(Integer.valueOf(String.valueOf(map.get("commentcounts"))));
        queryWrapper.select("SUM(ms_article.view_counts) as viewcounts");
        map=articleService.getMap(queryWrapper);
        me.setViewCounts(Integer.valueOf(String.valueOf(map.get("viewcounts"))));
        return Result.success(me);
    }


}
