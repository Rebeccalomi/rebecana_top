package com.rebecana.blog.admin.service;

import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Friend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-17
 */
public interface FriendService extends IService<Friend> {

    Result listFriend(PageParam pageParam);

    Result add(Friend friend);

    Result update(Friend friend);

    Result delete(Long id);
}
