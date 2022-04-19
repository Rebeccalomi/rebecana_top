package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Friend;
import com.rebecana.blog.admin.service.FriendService;
import com.rebecana.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zdy
 * @since 2022-04-17
 */
@RestController
@RequestMapping("/admin/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @PostMapping("/friendList")
    public Result listFriend(@RequestBody PageParam pageParam){
        return friendService.listFriend(pageParam);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Friend friend){
        return friendService.add(friend);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Friend friend){
        return friendService.update(friend);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return friendService.delete(id);
    }
}

