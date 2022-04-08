package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.mapper.RoleMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Role;
import com.rebecana.blog.admin.service.RolePermissionService;
import com.rebecana.blog.admin.service.RoleService;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import com.rebecana.blog.admin.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdy
 * @since 2022-04-08
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private  RoleMapper roleMapper;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public Result listRole(PageParam pageParam) {
        Page<Role> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(Role::getName,pageParam.getQueryString());
        }
        Page<Role> rolePage = roleMapper.selectPage(page, queryWrapper);
        PageResult<Role> pageResult = new PageResult<>();
        pageResult.setList(rolePage.getRecords());
        pageResult.setTotal(rolePage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result listRole(){
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        List<Role> roleList = roleMapper.selectList(queryWrapper);
        return Result.success(roleList);
    }

    public Role copy(RoleVo roleVo){
        Role role=new Role();
        role.setId(roleVo.getId());
        role.setName(roleVo.getName());
        return role;
    }

    @Override
    public Result add(RoleVo roleVo) {
        this.roleMapper.insert(copy(roleVo));
        rolePermissionService.deletebyrole(roleVo.getId());
        rolePermissionService.insertbypermission(roleVo.getPermission(),roleVo.getId());
        return Result.success(null);
    }

    @Override
    public Result update(RoleVo roleVo) {
        this.roleMapper.updateById(copy(roleVo));
        rolePermissionService.deletebyrole(roleVo.getId());
        rolePermissionService.insertbypermission(roleVo.getPermission(),roleVo.getId());
        return Result.success(null);
    }

    @Override
    public Result delete(Long id) {
        rolePermissionService.deletebyrole(id);
        this.roleMapper.deleteById(id);
        return Result.success(null);
    }
}
