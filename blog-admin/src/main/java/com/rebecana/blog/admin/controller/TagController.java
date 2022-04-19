package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Permission;
import com.rebecana.blog.admin.pojo.Tag;
import com.rebecana.blog.admin.service.TagService;
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
@RequestMapping("/admin/tag")
public class TagController {


    @Autowired
    private TagService tagService;

    @PostMapping("/tagList")
    public Result listPermission(@RequestBody PageParam pageParam){
        return tagService.listTag(pageParam);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Tag tag){
        return tagService.add(tag);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Tag tag){
        return tagService.update(tag);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return tagService.delete(id);
    }
}

