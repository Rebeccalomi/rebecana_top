package com.rebecana.blog.service;

import com.rebecana.blog.dao.pojo.SysUser;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.UserVo;
import com.rebecana.blog.dao.pojo.SysUser;

public interface SysUserService {

    SysUser findUserById(Long id);

    

}
