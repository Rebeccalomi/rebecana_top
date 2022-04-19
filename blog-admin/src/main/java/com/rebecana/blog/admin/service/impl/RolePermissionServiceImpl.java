package com.rebecana.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rebecana.blog.admin.mapper.PermissionMapper;
import com.rebecana.blog.admin.mapper.RoleMapper;
import com.rebecana.blog.admin.mapper.RolePermissionMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.RolePermission;
import com.rebecana.blog.admin.service.RolePermissionService;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import com.rebecana.blog.admin.vo.RolePermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void deletebyrole(Long id) {
        LambdaQueryWrapper<RolePermission> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId,id);
        rolePermissionMapper.delete(queryWrapper);
    }

    @Override
    public void insertbypermission(List<Long> permission,Long id) {
        for(Long permiss : permission){
            RolePermission rolePermission=new RolePermission();
            rolePermission.setRoleId(id);
            rolePermission.setPermissionId(permiss);
            rolePermissionMapper.insert(rolePermission);
        }
    }

    @Override
    public Result listrolePermission(PageParam pageParam) {
        Page<RolePermission> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            queryWrapper.eq(RolePermission::getId,pageParam.getQueryString());
        }
        Page<RolePermission> rolePermissionPage = rolePermissionMapper.selectPage(page, queryWrapper);
        PageResult<RolePermissionVo> pageResult = new PageResult<>();
        List<RolePermissionVo> rolePermissionVo=new ArrayList<>();
        for(RolePermission rolePermission:rolePermissionPage.getRecords()){
            RolePermissionVo rolePermissionvo=new RolePermissionVo();
            rolePermissionvo.setId(rolePermission.getId());
            rolePermissionvo.setRole(roleMapper.selectrole(rolePermission.getRoleId()));
            rolePermissionvo.setPermission(permissionMapper.selectpermission(rolePermission.getPermissionId()));
            rolePermissionVo.add(rolePermissionvo);
        }
        pageResult.setList(rolePermissionVo);
        pageResult.setTotal(rolePermissionPage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result delete(Long id) {
        this.rolePermissionMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result update(RolePermission rolePermission) {
        return Result.success(null);
    }

    @Override
    public Result add(RolePermission rolePermission) {
        return Result.success(null);
    }


}
