package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.RolePermission;
import com.rebecana.blog.admin.service.RolePermissionService;
import com.rebecana.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zdy
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/admin/role-permission")
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/List")
    public Result listArticle(@RequestBody PageParam pageParam){
        return rolePermissionService.listrolePermission(pageParam);
    }

    @PostMapping("/add")
    public Result add(@RequestBody RolePermission rolePermission){
        return rolePermissionService.add(rolePermission);
    }

    @PostMapping("/update")
    public Result update(@RequestBody RolePermission rolePermission){
        return rolePermissionService.update(rolePermission);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return rolePermissionService.delete(id);
    }


}

