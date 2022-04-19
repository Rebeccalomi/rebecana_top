package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.service.impl.DataService;
import com.rebecana.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/echart")
public class DataController {
    @Autowired
    private DataService dataservice;

    @PostMapping("/echart1")
    public Result echart1(){
        return dataservice.echart1();
    }

    @PostMapping("/echart2")
    public Result echart2(){
        return dataservice.echart2();
    }

}
