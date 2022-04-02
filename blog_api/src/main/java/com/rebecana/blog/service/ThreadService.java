package com.rebecana.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rebecana.blog.dao.mapper.ArticleMapper;
import com.rebecana.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {

    //期望此操作在线程池进行不会影响原有的主线程
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article){
//
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(article.getViewCounts() + 1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId,article.getId());
        //设置一个 为了在多线程的环境下线程安全
        //乐观锁，类似CAS操作，如果操作的时候发现与期望的阅读量不一致，修改失败
        //先查询，在修改的时候再查询一次，如果中途没有被修改过再执行+1
        queryWrapper.eq(Article::getViewCounts,article.getViewCounts());
        articleMapper.update(articleUpdate,queryWrapper);
        try {
            //睡眠5秒 证明不会影响主线程的使用
            Thread.sleep(5000);
            System.out.println("更新完成了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
