package com.fujimao.fclouddisk.common;


/**
 * 返回工具类
 */
public class ResultUtils {

    /**
     * 成功
     * @param data 返回数据
     * @return 通用返回类型（成功）
     * @param <T> 数据类型
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "success", "");
    }

    /**
     * 成功
     * @param data 返回数据
     * @param description 描述
     * @return 通用返回类型（成功）
     * @param <T> 数据类型
     */
    public static <T> BaseResponse<T> success(T data, String description) {
        return new BaseResponse<>(0, data, "success", description);
    }

    /**
     * 失败
     * @param errorCode 自定义异常
     * @return 通用返回类型（失败）
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     * @param errorCode 自定义异常
     * @param description 描述
     * @return 通用返回类型（失败）
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode, description);
    }

    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }
}
