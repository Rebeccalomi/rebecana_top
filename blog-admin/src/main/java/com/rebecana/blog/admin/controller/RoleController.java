package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.service.PermissionService;
import com.rebecana.blog.admin.service.RoleService;
import com.rebecana.blog.admin.vo.Result;
import com.rebecana.blog.admin.vo.RoleVo;
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
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/roleList")
    public Result listRole(@RequestBody PageParam pageParam){
        return roleService.listRole(pageParam);
    }

    @PostMapping("/add")
    public Result add(@RequestBody RoleVo roleVo){
        return roleService.add(roleVo);
    }

    @PostMapping("/update")
    public Result update(@RequestBody RoleVo roleVo){
        return roleService.update(roleVo);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return roleService.delete(id);
    }

    @PostMapping("/getpermission")
    public Result getPermission(){
        return permissionService.getPermission();
    }
}

