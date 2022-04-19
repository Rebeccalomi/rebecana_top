package com.rebecana.blog.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Role;
import com.rebecana.blog.admin.vo.Result;
import com.rebecana.blog.admin.vo.RoleVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-08
 */
public interface RoleService extends IService<Role> {

    Result listRole(PageParam pageParam);

    Result listRole();

    Result add(RoleVo RoleVo);

    Result update(RoleVo RoleVo);

    Result delete(Long id);
}
