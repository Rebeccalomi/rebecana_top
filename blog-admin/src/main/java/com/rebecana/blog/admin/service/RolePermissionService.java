package com.rebecana.blog.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.RolePermission;
import com.rebecana.blog.admin.vo.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-08
 */
public interface RolePermissionService extends IService<RolePermission> {

    void deletebyrole(Long id);

    void insertbypermission(List<Long> permission,Long id);

    Result listrolePermission(PageParam pageParam);

    Result delete(Long id);

    Result update(RolePermission rolePermission);

    Result add(RolePermission rolePermission);
}
