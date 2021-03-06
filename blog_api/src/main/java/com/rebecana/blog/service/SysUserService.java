package com.rebecana.blog.service;

import com.rebecana.blog.common.cache.Cache;
import com.rebecana.blog.dao.pojo.SysUser;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.UserVo;

public interface SysUserService {

    SysUser findUserById(Long id);

    SysUser findUser(String account,String password);

    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);

    /**
     * 根据用户查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 根据token查询
     * @param token
     * @return
     */
    Result findUserByToken(String token);


    UserVo findUserVoById(Long id);

    @Cache(expire = 5 * 60 * 1000,name = "about_me")
    Result findMe();
}
