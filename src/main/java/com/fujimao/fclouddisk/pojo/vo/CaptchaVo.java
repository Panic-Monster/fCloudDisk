package com.fujimao.fclouddisk.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
    @Schema(description = "生成图形验证码的唯一标识")
    private String uuid;
    /**
     * 生成路径
     */
    @Schema(description = "生成图形验证码路径")
    private String codeUrl;
}
