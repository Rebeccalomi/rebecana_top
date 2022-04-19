package com.rebecana.blog.controller;


import com.rebecana.blog.service.LoginService;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.params.LoginParam;
import com.rebecana.blog.vo.params.UpdateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    //按业务service，低耦合
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        //登陆验证用户，访问用户表
        return loginService.login(loginParam);
    }

    @PostMapping("update")
    public Result update(@RequestBody UpdateParam updateParam){
        return loginService.update(updateParam);
    }

}
