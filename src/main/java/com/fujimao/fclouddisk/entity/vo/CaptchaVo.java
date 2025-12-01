package com.fujimao.fclouddisk.entity.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author: Jayson_Y
 * @date: 2025/12/1
 * @project: fCloudDisk
 */
@Data
public class CaptchaVo {
    /**
     * uuid
     */
    private String uuid;
    /**
     * 生成路径
     */
    private String code;
}
