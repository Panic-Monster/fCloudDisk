package com.fujimao.fclouddisk.annotation;

import java.lang.annotation.*;

/**
 * 限流注解
 * @author: Jayson_Y
 * @date: 2025/12/1
 * @project: fCloudDisk
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    /**
     * 最大请求次数
     */
    int count() default 1;

    /**
     * 时间窗口，单位秒
     */
    int time() default 1;
}
