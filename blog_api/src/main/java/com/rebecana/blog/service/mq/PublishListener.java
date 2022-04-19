package com.rebecana.blog.service.mq;

import com.rebecana.blog.service.ArticleService;
import com.rebecana.blog.vo.ArticleMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RocketMQMessageListener(topic = "blog-publish-article",consumerGroup = "blog-publish-article-group")
public class PublishListener implements RocketMQListener<ArticleMessage> {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public void onMessage(ArticleMessage articleMessage) {
        Set<String> keys = redisTemplate.keys("list_article*");
        keys.forEach(s -> {
            redisTemplate.delete(s);
            log.info("删除了文章列表的缓存:{}",s);
        });
        Set<String> keys2 = redisTemplate.keys("new_article*");
        keys2.forEach(s -> {
            redisTemplate.delete(s);
            log.info("删除了最新文章的缓存:{}",s);
        });
    }
}
