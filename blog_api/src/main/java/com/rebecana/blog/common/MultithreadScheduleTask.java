package com.rebecana.blog.common;

import com.rebecana.blog.dao.mapper.DateStaticMapper;
import com.rebecana.blog.dao.pojo.DateStatic;
import com.rebecana.blog.utils.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

//@Component注解用于对那些比较中立的类进行注释；
//相对与在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
@Slf4j
public class MultithreadScheduleTask {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DateStaticMapper dateStaticMapper;

    @Scheduled(cron = "0 59 23 1/1 * ?")  //每天23.59
    public void first() throws InterruptedException {
        System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(date);
        String viewCounts= (String) redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_VIEW_COUNTS,str);
        String commentCounts= (String) redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_USER_COMMENT_COUNTS,str);
        DateStatic dateStatic=new DateStatic();
        dateStatic.setDate(date);
        dateStatic.setCommentCounts(commentCounts);
        dateStatic.setViewCounts(viewCounts);
        dateStaticMapper.insert(dateStatic);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_COMMENT_COUNTS,str);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_VIEW_COUNTS,str);
        Thread.sleep(1000 * 10);
    }
}