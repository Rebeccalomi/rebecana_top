package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.ArticleTag;
import com.rebecana.blog.admin.pojo.Category;
import com.rebecana.blog.admin.service.ArticleTagService;
import com.rebecana.blog.admin.service.CategoryService;
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
@RequestMapping("/admin/article-tag")
public class ArticleTagController {
    @Autowired
    private ArticleTagService articleTagService;

    @PostMapping("/articleTagList")
    public Result listPermission(@RequestBody PageParam pageParam){
        return articleTagService.listArticleTag(pageParam);
    }

    @PostMapping("/add")
    public Result add(@RequestBody ArticleTag articleTag){
        return articleTagService.add(articleTag);
    }

    @PostMapping("/update")
    public Result update(@RequestBody ArticleTag articleTag){
        return articleTagService.update(articleTag);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return articleTagService.delete(id);
    }
}

