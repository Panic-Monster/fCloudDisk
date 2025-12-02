package com.fujimao.fclouddisk.pojo.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author: Jayson_Y
 * @date: 2025/12/1
 * @project: fCloudDisk
 */
@Component
@ConfigurationProperties(prefix = "captcha")
@Data
public class CaptchaDto {
    /**
     * 宽度
     */
    private int width;
    /**
     * 高度
     */
    private int height;
    /**
     * 验证码位数
     */
    private int count;
    /**
     * 干扰线位数
     */
    private int lineCount;
    /**
     * 生成路径
     */
    private String url;
}
