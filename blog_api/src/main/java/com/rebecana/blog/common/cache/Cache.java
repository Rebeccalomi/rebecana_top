package com.rebecana.blog.common.cache;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    //内存的访问速度 远远大于 磁盘的访问速度 （1000倍起）,过期时间
    long expire() default 1 * 60 * 1000;

    //缓存标识
    String name() default "";

}