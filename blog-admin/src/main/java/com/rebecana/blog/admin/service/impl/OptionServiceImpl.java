package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Option;
import com.rebecana.blog.admin.mapper.OptionMapper;
import com.rebecana.blog.admin.service.OptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-04-21
 */
@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements OptionService {
    @Autowired
    private OptionMapper optionMapper;

    @Override
    public Result listMessage() {
        LambdaQueryWrapper<Option> queryWrapper=new LambdaQueryWrapper<>();
        Option option=optionMapper.selectOne(queryWrapper);
        return Result.success(option);
    }

    @Override
    public Result update(Option option) {
        this.optionMapper.updateById(option);
        return Result.success(null);
    }
}
