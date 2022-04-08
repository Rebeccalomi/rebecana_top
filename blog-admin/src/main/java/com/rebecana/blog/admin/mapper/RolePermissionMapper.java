package com.rebecana.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rebecana.blog.admin.pojo.RolePermission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdy
 * @since 2022-04-08
 */
@Component
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    @Select("SELECT permission_id FROM ms_role_permission where role_id=#{role}")
    List<Long> selectbyadmin(Long role);
}
