package com.fujimao.fclouddisk.service;

import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.entity.vo.CaptchaVo;

/**
 * @author: Jayson_Y
 * @date: 2025/12/1
 * @project: fCloudDisk
 */
public interface CaptchaService {

    /**
     * 生成图形验证码
     * @return 返回图形验证码图片地址
     */
    String generateCaptcha();

    /**
     * 检查图形验证码
     * @param captchaCode 验证码
     * @param uuid uuid
     * @return
     */
    boolean checkCaptcha(String captchaCode, String uuid);
}
