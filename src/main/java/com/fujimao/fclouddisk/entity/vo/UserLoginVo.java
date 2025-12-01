package com.fujimao.fclouddisk.entity.vo;

import lombok.Data;

@Data
public class UserLoginVo {

    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 图形验证码
     */
    private String captchaCode;

    /**
     * 图形码的uuid
     */
    private String verifyUuid;
}
