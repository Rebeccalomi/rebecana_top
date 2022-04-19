package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.mapper.FriendMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Friend;
import com.rebecana.blog.admin.service.FriendService;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-04-17
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {

    @Autowired
    private FriendMapper mapper;

    @Override
    public Result listFriend(PageParam pageParam) {
        Page<Friend> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Friend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Friend::getCreateDate);
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(Friend::getNickname,pageParam.getQueryString());
        }
        Page<Friend> friendPage = mapper.selectPage(page, queryWrapper);
        PageResult<Friend> pageResult = new PageResult<>();
        pageResult.setList(friendPage.getRecords());
        pageResult.setTotal(friendPage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result add(Friend friend) {
        mapper.insert(friend);
        return Result.success(null);
    }

    @Override
    public Result update(Friend friend) {
        mapper.updateById(friend);
        return Result.success(null);
    }

    @Override
    public Result delete(Long id) {
        mapper.deleteById(id);
        return Result.success(null);
    }
}
