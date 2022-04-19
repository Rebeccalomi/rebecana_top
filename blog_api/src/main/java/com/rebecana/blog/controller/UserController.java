package com.rebecana.blog.controller;

import com.rebecana.blog.service.SysUserService;
import com.rebecana.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){
        return sysUserService.findUserByToken(token);
    }

    @PostMapping("me")
    public Result Me(){
        return sysUserService.findMe();
    }
}

