package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.mapper.AdminMapper;
import com.rebecana.blog.admin.mapper.ArticleMapper;
import com.rebecana.blog.admin.pojo.Admin;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.pojo.Permission;
import com.rebecana.blog.admin.service.AdminService;
import com.rebecana.blog.admin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin findAdminByUsername(String username){
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername,username);
        queryWrapper.last("limit 1");
        Admin admin = (Admin) adminMapper.selectOne(queryWrapper);
        return admin;
    }

    public List<Permission> findPermissionByAdminId(Long adminId) {
        //SELECT * FROM `ms_permission` where id in (select permission_id from ms_admin_permission where admin_id=1)
        return adminMapper.findPermissionByAdminId(adminId);
    }
}
