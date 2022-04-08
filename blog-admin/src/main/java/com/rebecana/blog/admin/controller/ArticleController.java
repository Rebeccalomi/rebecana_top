package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.service.ArticleService;
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
@RequestMapping("/admin/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/articleList")
    public Result listArticle(@RequestBody PageParam pageParam){
        return articleService.listArticle(pageParam);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Article article){
        return articleService.add(article);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Article article){
        return articleService.update(article);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return articleService.delete(id);
    }
}

