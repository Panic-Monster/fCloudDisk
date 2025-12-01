package com.fujimao.fclouddisk.controller;

import com.fujimao.fclouddisk.annotation.RateLimit;
import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.common.ResultUtils;
import com.fujimao.fclouddisk.service.CaptchaService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jayson_Y
 * @date: 2025/12/1
 * @project: fCloudDisk
 */
@RestController
@RequestMapping("captcha")
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    /**
     * 生成图形验证码
     * @return
     */
    @RateLimit(count = 1, time = 1)
    @PostMapping("/generate")
    public BaseResponse<String> generateCaptcha() {
        String generateCaptchaUrl = captchaService.generateCaptcha();
        return ResultUtils.success(generateCaptchaUrl);
    }
}
