package com.rebecana.blog.controller;


import com.rebecana.blog.service.MessageService;
import com.rebecana.blog.vo.MessageVo;
import com.rebecana.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zdy
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("getmsg")
    public Result Me(){
        return messageService.find();
    }


    @PostMapping("publish")
    public Result publish(@RequestBody MessageVo messageVo){
        return messageService.publish(messageVo);
    }
}

