package com.fujimao.fclouddisk.aop;

import com.fujimao.fclouddisk.annotation.RateLimit;
import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.common.ErrorCode;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Jayson_Y
 * @date: 2025/12/1
 * @project: fCloudDisk
 */
@Aspect
@Component
public class RateLimitAspect {

    // key: 方法签名, value: RateLimiter
    private final Map<String, RateLimiter> limiters  = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        // 不同接口有不同的签名，互不影响
        String key = joinPoint.getSignature().toShortString();
        // 计算每秒允许的请求数
        double permitsPerSecond = (double) rateLimit.count() / rateLimit.time();
        // 获取或创建限流器
        RateLimiter rateLimiter = limiters.computeIfAbsent(key, k -> RateLimiter.create(permitsPerSecond));
        // 尝试获取令牌
        if (!rateLimiter.tryAcquire()) {
            // todo 可以返回自定义响应，而不是抛异常
            // throw new BaseResponse<>(ErrorCode.TOO_MANY_REQUESTS);
            throw new RuntimeException("请求过于频繁，请稍后重试");
        }
        return joinPoint.proceed();
    }
}
