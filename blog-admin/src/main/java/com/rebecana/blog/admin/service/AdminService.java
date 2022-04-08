package com.rebecana.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.mapper.AdminMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Admin;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.pojo.Permission;
import com.rebecana.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService extends IService<Admin> {


    public Admin findAdminByUsername(String username);

    public List<Permission> findPermissionByAdminId(Long adminId);

    Result listAdmin(PageParam pageParam);

    Result add(Admin admin);

    Result update(Admin admin);

    Result delete(Long id);

    Result getusernow();
}
