package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Message;
import com.rebecana.blog.admin.service.MessageService;
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
@RequestMapping("/admin/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/messageList")
    public Result listFriend(@RequestBody PageParam pageParam){
        return messageService.listMessage(pageParam);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Message message){
        return messageService.add(message);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Message message){
        return messageService.update(message);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return messageService.delete(id);
    }
}

