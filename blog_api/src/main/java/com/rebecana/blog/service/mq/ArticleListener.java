package com.rebecana.blog.service.mq;

import com.alibaba.fastjson.JSON;
import com.rebecana.blog.service.ArticleService;
import com.rebecana.blog.vo.ArticleMessage;
import com.rebecana.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;

//接收消息队列的消息
@Slf4j
@Component
@RocketMQMessageListener(topic = "blog-update-article",consumerGroup = "blog-update-article-group")
public class ArticleListener implements RocketMQListener<ArticleMessage> {  //泛型为发送的消息

    @Autowired
    private ArticleService articleService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onMessage(ArticleMessage message) {
        log.info("收到的消息:{}",message);
        //做什么了，更新缓存
        //1. 更新查看文章详情的缓存
        Long articleId = message.getArticleId();
        String params = DigestUtils.md5Hex(articleId.toString());
        String redisKey = "view_article::ArticleController::findArticleById::"+params;
        Result articleResult = articleService.findArticleById(articleId);
        redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(articleResult), Duration.ofMillis(5 * 60 * 1000));
        log.info("更新了缓存:{}",redisKey);
        //2. 文章列表的缓存 不知道参数,解决办法 直接删除缓存
        Set<String> keys = redisTemplate.keys("list_article*");
        keys.forEach(s -> {
            redisTemplate.delete(s);
            log.info("删除了文章列表的缓存:{}",s);
        });
        Set<String> keys1 = redisTemplate.keys("hot_article*");
        keys1.forEach(s -> {
            redisTemplate.delete(s);
            log.info("删除了最热文章的缓存:{}",s);
        });
        Set<String> keys2 = redisTemplate.keys("new_article*");
        keys2.forEach(s -> {
            redisTemplate.delete(s);
            log.info("删除了最新文章的缓存:{}",s);
        });
    }
}

