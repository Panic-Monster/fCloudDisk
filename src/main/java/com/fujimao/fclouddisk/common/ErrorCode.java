package com.fujimao.fclouddisk.common;


import lombok.Data;
import lombok.Getter;

/**
 * 错误码
 */
@Getter
public enum ErrorCode {

    SUCCESS(0, "success", ""),
    PARAM_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40010, "请求参数为空", ""),
    NO_LOGIN(40100, "未登录", ""),
    NO_REGISTER(40101, "未注册", ""),
    NO_AUTH(40102, "无权限", ""),
    ;

    private final int code;
    /**
     * 状态码信息
     */
    private final String message;
    /**
     * 状态码描述（详细）
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

}
