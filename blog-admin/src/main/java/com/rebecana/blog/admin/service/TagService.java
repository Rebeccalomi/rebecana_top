package com.rebecana.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rebecana.blog.admin.mapper.PermissionMapper;
import com.rebecana.blog.admin.mapper.TagMapper;
import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Permission;
import com.rebecana.blog.admin.pojo.SysUser;
import com.rebecana.blog.admin.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.vo.PageResult;
import com.rebecana.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
public interface TagService extends IService<Tag> {

    public Result listTag(PageParam pageParam);

    public Result add(Tag tag);

    public Result delete(Long id);

    public Result update(Tag tag);
}
