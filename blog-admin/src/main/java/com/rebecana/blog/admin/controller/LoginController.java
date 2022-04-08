package com.rebecana.blog.admin.controller;

import com.rebecana.blog.admin.filter.VerificationCodeException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/loginin")
    public String login(){
        return "login.html";
    }

    @RequestMapping("/login")
    public String loginfail(VerificationCodeException e){
        return "/loginin";
    }
}
