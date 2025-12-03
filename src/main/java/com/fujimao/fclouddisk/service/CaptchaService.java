package com.fujimao.fclouddisk.service;

import com.fujimao.fclouddisk.pojo.vo.CaptchaVo;

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
    CaptchaVo generateCaptcha();

    /**
     * 检查图形验证码
     * @param captchaCode 验证码
     * @param uuid uuid
     * @return
     */
    boolean checkCaptcha(String captchaCode, String uuid);

    /**
     * 通过邮箱发送邮件
     * @param email
     * @return
     */
    boolean sendEmailForCaptcha(String email);

    /**
     * 验证邮箱发送的验证码
     * @param email
     * @return
     */
    boolean validateCode(String email, String inputCode);
}
