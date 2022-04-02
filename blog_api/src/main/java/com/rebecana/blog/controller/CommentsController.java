package com.rebecana.blog.controller;


import com.rebecana.blog.service.CommentsService;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
public class CommentsController {

        @Autowired
        private CommentsService commentsService;

        @GetMapping("article/{id}")
        public Result comments(@PathVariable("id") Long articleId) {
            System.out.println("111");
            return commentsService.commentsByArticleId(articleId);
        }


        @PostMapping("create/change")
        public Result comment(@RequestBody CommentParam commentParam){
            return commentsService.comment(commentParam);
        }
}

