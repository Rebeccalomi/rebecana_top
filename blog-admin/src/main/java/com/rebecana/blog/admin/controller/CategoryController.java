package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Category;
import com.rebecana.blog.admin.pojo.Tag;
import com.rebecana.blog.admin.service.CategoryService;
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
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categoryList")
    public Result listPermission(@RequestBody PageParam pageParam){
        return categoryService.listTag(pageParam);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Category category){
        return categoryService.add(category);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Category category){
        return categoryService.update(category);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return categoryService.delete(id);
    }

}

