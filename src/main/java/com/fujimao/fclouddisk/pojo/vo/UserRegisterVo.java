package com.fujimao.fclouddisk.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: Jayson_Y
 * @date: 2025/12/2
 * @project: fCloudDisk
 */
@Data
public class UserRegisterVo {

    /**
     * 注册邮箱
     */
    @Schema(description = "注册邮箱", example = "xxx@qq.com")
    private String email;
    /**
     * 邮箱发送的验证码
     */
    @Schema(description = "邮箱发送的验证码", example = "123456")
    private String emailCode;
    /**
     * 用户注册昵称
     */
    @Schema(description = "用户注册昵称", example = "小小呆")
    private String nickName;
    /**
     * 密码
     */
    @Schema(description = "密码", example = "xxxxxx")
    private String password;
    /**
     * 二次验证密码
     */
    @Schema(description = "验证密码", example = "xxxxxx")
    private String verificationPassword;
}
