package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Message;
import com.rebecana.blog.admin.pojo.Option;
import com.rebecana.blog.admin.service.OptionService;
import com.rebecana.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zdy
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/admin/option")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @PostMapping("/get")
    public Result listFriend(){
        return optionService.listMessage();
    }


    @PostMapping("/update")
    public Result update(@RequestBody Option option){
        return optionService.update(option);
    }

}

