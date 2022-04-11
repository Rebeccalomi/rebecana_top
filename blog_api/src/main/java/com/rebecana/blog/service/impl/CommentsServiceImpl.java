package com.rebecana.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rebecana.blog.dao.mapper.ArticleMapper;
import com.rebecana.blog.dao.mapper.CommentMapper;
import com.rebecana.blog.dao.pojo.Article;
import com.rebecana.blog.dao.pojo.Comment;
import com.rebecana.blog.dao.pojo.SysUser;
import com.rebecana.blog.service.CommentsService;
import com.rebecana.blog.service.SysUserService;
import com.rebecana.blog.utils.RedisKeyUtils;
import com.rebecana.blog.utils.UserThreadLocal;
import com.rebecana.blog.vo.CommentVo;
import com.rebecana.blog.vo.Result;
import com.rebecana.blog.vo.UserVo;
import com.rebecana.blog.vo.params.CommentParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private SysUserService sysUserService;

    public CommentVo copy(Comment comment){
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        commentVo.setId(comment.getId().toString());
        //作者信息
        //时间格式化
        commentVo.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        Long authorId = comment.getAuthorId();
        UserVo userVo = this.sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        //子评论
        Integer level = comment.getLevel();
        if (1 == level){
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        }
        //to User 给谁评论
        if (level > 1){
            Long toUid = comment.getToUid();
            UserVo toUserVo = this.sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId,id);
        queryWrapper.eq(Comment::getLevel,2);
        List<Comment> comments = this.commentMapper.selectList(queryWrapper);
        return copyList(comments);
    }

    public List<CommentVo> copyList(List<Comment> commentList){
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }


    @Override
    public Result commentsByArticleId(Long articleId) {
        /**
         * 1.根据文章id查询comment评论列表
         * 2。根据作者的id查询作者信息
         * 3。判断如果level=1，要去查询它有没有子评论
         * 4。有的话根据评论id，进行查询
         */
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,articleId);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return Result.success(copyList(comments));
    }

    @Override
    public Result comment(CommentParam commentParam) {
        SysUser sysUser= UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        }else{
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        this.commentMapper.insert(comment);
        UpdateWrapper<Article> updateWrapper = Wrappers.update();
        updateWrapper.eq("id",comment.getArticleId());
        updateWrapper.setSql(true,"comment_counts=comment_counts+1");
        this.articleMapper.update(null,updateWrapper);
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(date);
        if (redisTemplate.opsForHash().hasKey(RedisKeyUtils.MAP_KEY_USER_COMMENT_COUNTS,str)) {
            redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_COMMENT_COUNTS, str, 1);
        }else{
            redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_COMMENT_COUNTS,str,"1");
        }
        return Result.success(null);
    }
}
