package com.fujimao.fclouddisk.configs;

import com.fujimao.fclouddisk.aop.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Jayson_Y
 * @date: 2025/12/1
 * @project: fCloudDisk
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/login",
                        "/doc.html",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**"
                );
    }
}
