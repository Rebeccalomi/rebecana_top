package com.rebecana.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rebecana.blog.admin.pojo.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdy
 * @since 2022-04-08
 */
@Component
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT name FROM ms_role where id=#{id}")
    String selectrole(Long id);

}
