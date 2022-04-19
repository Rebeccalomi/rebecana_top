package com.rebecana.blog.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Permission;
import com.rebecana.blog.admin.vo.Result;
import org.springframework.stereotype.Service;


@Service
public interface PermissionService extends IService<Permission> {


    public Result listPermission(PageParam pageParam);

    public Result add(Permission permission);

    public Result update(Permission permission);

    public Result delete(Long id);

    Result getPermission();
}
