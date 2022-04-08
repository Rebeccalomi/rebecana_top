package com.rebecana.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rebecana.blog.admin.pojo.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT name FROM ms_permission where id=#{permissionId}")
    String selectpermission(Long permissionId);

}
