package com.rebecana.blog.service;


import com.rebecana.blog.dao.pojo.SysUser;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.params.LoginParam;
import com.rebecana.blog.vo.params.UpdateParam;
import org.springframework.transaction.annotation.Transactional;

@Transactional //事务失败回滚
public interface LoginService {

    /**
     * 登陆功能
     *
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);
    Result logout(String token);
    Result register(LoginParam loginParam);

    SysUser checkToken(String token);

    Result update(UpdateParam updateParam);
}
