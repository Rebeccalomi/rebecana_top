package com.rebecana.blog.admin.controller;

import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Admin;
import com.rebecana.blog.admin.pojo.Permission;
import com.rebecana.blog.admin.service.AdminService;
import com.rebecana.blog.admin.service.PermissionService;
import com.rebecana.blog.admin.service.RoleService;
import com.rebecana.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @PostMapping("user/userInfo")
    public Result getuser(){
        return adminService.getusernow();
    }

    @PostMapping("permission/permissionList")
    public Result listPermission(@RequestBody PageParam pageParam){
        return permissionService.listPermission(pageParam);
    }

    @PostMapping("permission/add")
    public Result add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @PostMapping("permission/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }

    @PostMapping("admin/adminList")
    public Result listAdmin(@RequestBody PageParam pageParam){
        return adminService.listAdmin(pageParam);
    }

    @PostMapping("admin/add")
    public Result add1(@RequestBody Admin admin){
        return adminService.add(admin);
    }

    @PostMapping("admin/update")
    public Result update1(@RequestBody  Admin admin){
        return adminService.update(admin);
    }

    @GetMapping("admin/delete/{id}")
    public Result delete1(@PathVariable("id") Long id){
        return adminService.delete(id);
    }

    @PostMapping("admin/getrole")
    public Result listArticle(){
        return roleService.listRole();
    }

}
