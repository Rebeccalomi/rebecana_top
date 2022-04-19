package com.rebecana.blog.controller;


import com.rebecana.blog.service.FriendService;
import com.rebecana.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zdy
 * @since 2022-04-16
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("get")
    public Result Me(){
        return friendService.find();
    }

}

