package com.rebecana.blog.admin.controller;


import com.rebecana.blog.admin.model.params.PageParam;
import com.rebecana.blog.admin.pojo.Article;
import com.rebecana.blog.admin.pojo.Comment;
import com.rebecana.blog.admin.service.ArticleService;
import com.rebecana.blog.admin.service.CommentService;
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
@RequestMapping("/admin/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/commentList")
    public Result listComment(@RequestBody PageParam pageParam){
        return commentService.listComment(pageParam);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Comment comment){
        return commentService.add(comment);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Comment comment){
        return commentService.update(comment);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return commentService.delete(id);
    }

}

