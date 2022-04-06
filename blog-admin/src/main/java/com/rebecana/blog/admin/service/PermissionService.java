package com.rebecana.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.controller.AdminController;
import com.rebecana.blog.admin.mapper.PermissionMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Comment;
import com.rebecana.blog.admin.pojo.Permission;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;


@Service
public interface PermissionService extends IService<Permission> {


    public Result listPermission(PageParam pageParam);

    public Result add(Permission permission);

    public Result update(Permission permission);

    public Result delete(Long id);
}
