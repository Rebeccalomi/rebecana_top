package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rebecana.blog.admin.mapper.ArticleMapper;
import com.rebecana.blog.admin.mapper.DateStaticMapper;
import com.rebecana.blog.admin.pojo.DateStatic;
import com.rebecana.blog.admin.vo.Echart1Vo;
import com.rebecana.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private DateStaticMapper dateStaticMapper;

    public Result echart1() {
        List<Echart1Vo> echart1=articleMapper.selectechart1();
        return Result.success(echart1);
    }

    public Result echart2() {
        LambdaQueryWrapper<DateStatic> queryWrapper = new LambdaQueryWrapper<>();
        List<DateStatic> echart2=dateStaticMapper.selectList(queryWrapper);
        return Result.success(echart2);
    }
}
