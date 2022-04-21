package com.rebecana.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rebecana.blog.dao.pojo.Message;
import com.rebecana.blog.dao.pojo.Option;
import com.rebecana.blog.dao.mapper.OptionMapper;
import com.rebecana.blog.service.OptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.vo.MessageVo;
import com.rebecana.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-04-21
 */
@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionMapper optionMapper;

    @Override
    public Result find() {
        LambdaQueryWrapper<Option> queryWrapper=new LambdaQueryWrapper<>();
        Option option=optionMapper.selectOne(queryWrapper);
        return Result.success(option);
    }
}
