package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.pojo.SysUser;
import com.rebecana.blog.admin.service.ArticleService;
import com.rebecana.blog.admin.service.SysUserService;
import com.rebecana.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zdy
 * @since 2022-04-05
 */
@RestController
@RequestMapping("/admin/sysuser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/sysuserList")
    public Result listArticle(@RequestBody PageParam pageParam){
        return sysUserService.listArticle(pageParam);
    }

    @PostMapping("/add")
    public Result add(@RequestBody SysUser sysUser){
        return sysUserService.add(sysUser);
    }

    @PostMapping("/update")
    public Result update(@RequestBody SysUser sysUser){
        return sysUserService.update(sysUser);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return sysUserService.delete(id);
    }

}

