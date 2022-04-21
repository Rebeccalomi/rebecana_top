package com.rebecana.blog.admin.service;

import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Option;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rebecana.blog.admin.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdy
 * @since 2022-04-21
 */
public interface OptionService extends IService<Option> {

    Result listMessage();

    Result update(Option option);
}
