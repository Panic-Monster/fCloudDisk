package com.fujimao.fclouddisk.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserLoginVo {

    /**
     * 邮箱
     */
    @Schema(description = "邮箱", example = "xiaoming@example.com")
    private String email;
    /**
     * 密码
     */
    @Schema(description = "密码", example = "123456")
    private String password;
    /**
     * 图形验证码
     */
    @Schema(description = "验证码", example = "1234")
    private String captchaCode;

    /**
     * 图形码的uuid
     */
    @Schema(description = "验证码唯一标识", example = "5b381f46-0d3d-48c2-9f42-56ecd889b3af")
    private String verifyUuid;
}
