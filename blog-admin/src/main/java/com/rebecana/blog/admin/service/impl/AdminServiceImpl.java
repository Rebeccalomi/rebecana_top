package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.mapper.AdminMapper;
import com.rebecana.blog.admin.mapper.AdminPermissionMapper;
import com.rebecana.blog.admin.mapper.RoleMapper;
import com.rebecana.blog.admin.mapper.RolePermissionMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Admin;
import com.rebecana.blog.admin.pojo.AdminPermission;
import com.rebecana.blog.admin.pojo.Permission;
import com.rebecana.blog.admin.pojo.Role;
import com.rebecana.blog.admin.service.AdminService;
import com.rebecana.blog.admin.vo.AdminVo;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;


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

    @Override
    public Result listAdmin(PageParam pageParam) {
        Page<Admin> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(Admin::getUsername,pageParam.getQueryString());
        }
        Page<Admin> AdminPage = adminMapper.selectPage(page, queryWrapper);
        PageResult<AdminVo> pageResult = new PageResult<>();
        List<AdminVo> adminVo=new ArrayList<>();
        for(Admin admin:AdminPage.getRecords()){
            AdminVo admin1=new AdminVo();
            admin1.setId(admin.getId());
            admin1.setAvatar(admin.getAvatar());
            admin1.setPassword(admin.getPassword());
            admin1.setUsername(admin.getUsername());
            LambdaQueryWrapper<Role> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Role::getId,admin.getRole());
            Role role=roleMapper.selectOne(queryWrapper1);
            admin1.setRole(role.getName());
            adminVo.add(admin1);
        }
        pageResult.setList(adminVo);
        pageResult.setTotal(AdminPage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result add(Admin admin) {
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        this.adminMapper.insert(admin);
        List<Long> premissionList=rolePermissionMapper.selectbyadmin(admin.getRole());
        for(Long permission : premissionList){
            AdminPermission adminPermission=new AdminPermission();
            adminPermission.setAdminId(admin.getId());
            adminPermission.setPermissionId(permission);
            adminPermissionMapper.insert(adminPermission);
        }
        return Result.success(null);
    }

    @Override
    public Result update(Admin admin) {
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        this.adminMapper.updateById(admin);
        LambdaQueryWrapper<AdminPermission> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminPermission::getAdminId,admin.getId());
        adminPermissionMapper.delete(queryWrapper);
        List<Long> premissionList=rolePermissionMapper.selectbyadmin(admin.getRole());
        for(Long permission : premissionList){
            AdminPermission adminPermission=new AdminPermission();
            adminPermission.setAdminId(admin.getId());
            adminPermission.setPermissionId(permission);
            adminPermissionMapper.insert(adminPermission);
        }
        return Result.success(null);
    }

    @Override
    public Result delete(Long id) {
        this.adminMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result getusernow() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            LambdaQueryWrapper<Admin> queryWrapper=new LambdaQueryWrapper();
            queryWrapper.eq(Admin::getUsername,currentUserName);
            Admin admin=adminMapper.selectOne(queryWrapper);
            AdminVo adminVo=new AdminVo();
            adminVo.setUsername(admin.getUsername());
            adminVo.setAvatar(admin.getAvatar());
            adminVo.setId(admin.getId());
            adminVo.setRole(roleMapper.selectrole(admin.getRole()));
            adminVo.setPassword(admin.getPassword());
            return Result.success(adminVo);
        }else {
            throw new RuntimeException("No User");
        }
    }
}
