package com.rebecana.blog.controller;


import com.rebecana.blog.service.OptionService;
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
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/option")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @PostMapping("getOption")
    public Result Me(){
        return optionService.find();
    }
}

