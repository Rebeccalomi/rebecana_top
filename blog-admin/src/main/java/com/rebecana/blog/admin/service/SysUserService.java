package com.rebecana.blog.admin.service;

import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
public interface SysUserService extends IService<SysUser> {

    Result listArticle(PageParam pageParam);

    Result add(SysUser sysUser);

    Result update(SysUser sysUser);

    Result delete(Long id);
}
