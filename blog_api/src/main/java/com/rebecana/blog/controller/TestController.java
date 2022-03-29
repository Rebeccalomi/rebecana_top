package com.rebecana.blog.controller;

import com.rebecana.blog.dao.pojo.SysUser;
import com.rebecana.blog.utils.UserThreadLocal;
import com.rebecana.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test(){
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}
