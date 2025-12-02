package com.fujimao.fclouddisk.controller;

import com.fujimao.fclouddisk.annotation.RateLimit;
import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.common.ResultUtils;
import com.fujimao.fclouddisk.pojo.vo.CaptchaVo;
import com.fujimao.fclouddisk.service.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "验证码接口",description = "验证码相关操作，如生成验证码")
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    /**
     * 生成图形验证码
     * @return
     */
    @Operation(summary = "生成验证码", description = "生成图形化的验证码")
    @RateLimit(count = 1, time = 1)
    @PostMapping("/generate")
    public BaseResponse<CaptchaVo> generateCaptcha() {
        CaptchaVo captchaVo = captchaService.generateCaptcha();
        return ResultUtils.success(captchaVo);
    }
}
