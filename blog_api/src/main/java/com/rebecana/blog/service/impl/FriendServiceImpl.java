package com.rebecana.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rebecana.blog.dao.mapper.FriendMapper;
import com.rebecana.blog.dao.pojo.Friend;
import com.rebecana.blog.service.FriendService;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.FriendVo;
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
 * @since 2022-04-16
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;


    @Override
    public Result find() {
        LambdaQueryWrapper<Friend> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Friend::getCreateDate);
        List<Friend> friendList=friendMapper.selectList(queryWrapper);
        List<FriendVo> friendVoList=new ArrayList<>();
        for(Friend friend : friendList){
            FriendVo friendVo=new FriendVo();
            friendVo.setNickname(friend.getNickname());
            friendVo.setTitle(friend.getTitle());
            friendVo.setAvatar(friend.getAvatar());
            friendVo.setUrl(friend.getUrl());
            friendVoList.add(friendVo);
        }
        return Result.success(friendVoList);
    }
}
