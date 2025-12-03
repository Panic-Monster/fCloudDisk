package com.fujimao.fclouddisk.common;


import lombok.Getter;

/**
 * 错误码
 */
@Getter
public enum ErrorCode {

    // -------------------- 通用 --------------------
    SUCCESS(0, "success", ""),
    PARAM_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40010, "请求参数为空", ""),
    OPERATION_ERROR(40020, "操作失败", ""),
    TOO_MANY_REQUESTS(42900, "请求过于频繁，请稍后重试", ""),

    // -------------------- 用户认证 --------------------
    NO_LOGIN(40100, "未登录", ""),
    NO_REGISTER(40101, "未注册", ""),
    NO_AUTH(40102, "无权限", ""),
    PASSWORD_ERROR(40103, "密码错误", ""),
    TOKEN_EXPIRED(40104, "Token 已过期", ""),
    TOKEN_INVALID(40105, "Token 无效", ""),

    // -------------------- 验证码相关 --------------------
    CAPTCHA_ERROR(40110, "验证码错误", ""),
    CAPTCHA_EXPIRED(40111, "验证码已过期", ""),
    CAPTCHA_NOT_SENT(40112, "验证码未发送", ""),
    CAPTCHA_SENT_FAIL(40112, "验证码发送失败", ""),
    CAPTCHA_TOO_FREQUENT(40113, "验证码请求过于频繁", ""),

    // -------------------- 业务逻辑 --------------------
    USER_EXISTS(40200, "用户已存在", ""),
    USER_NOT_EXISTS(40201, "用户不存在", ""),

    // -------------------- 数据操作 --------------------
    DATA_NOT_FOUND(40400, "数据未找到", ""),
    DATA_DUPLICATE(40401, "数据重复", ""),
    DATA_SAVE_FAIL(40402, "数据保存失败", ""),
    DATA_UPDATE_FAIL(40403, "数据更新失败", ""),
    DATA_DELETE_FAIL(40404, "数据删除失败", ""),

    // -------------------- 系统异常 --------------------
    SYSTEM_ERROR(50000, "系统异常", ""),
    SERVICE_UNAVAILABLE(50001, "服务不可用", ""),
    TIMEOUT_ERROR(50002, "请求超时", ""),
    DATABASE_ERROR(50003, "数据库异常", ""),
    IO_ERROR(50004, "IO 异常", ""),

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
