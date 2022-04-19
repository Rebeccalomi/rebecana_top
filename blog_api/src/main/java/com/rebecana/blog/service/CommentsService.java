package com.rebecana.blog.service;

import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.params.CommentParam;

public interface CommentsService {

    Result commentsByArticleId(Long articleId);

    Result comment(CommentParam commentParam);
}
