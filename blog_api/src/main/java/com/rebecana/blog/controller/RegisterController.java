package com.rebecana.blog.controller;


import com.rebecana.blog.service.LoginService;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result register(@RequestBody LoginParam loginParam) {
        //sso 单点登录，后期如果把登陆注册功能提出去（单独的服务，可以独立提供借口）
        return loginService.register(loginParam);
    }
}
