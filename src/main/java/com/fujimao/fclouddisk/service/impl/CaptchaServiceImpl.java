package com.fujimao.fclouddisk.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.fujimao.fclouddisk.common.ErrorCode;
import com.fujimao.fclouddisk.pojo.dto.CaptchaDto;
import com.fujimao.fclouddisk.exception.ThrowUtils;
import com.fujimao.fclouddisk.pojo.vo.CaptchaVo;
import com.fujimao.fclouddisk.service.CaptchaService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @author Administrator
 * @description 针对【账号相关】的数据库操作Service实现
 * @createDate 2025-11-29 09:53:19
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    // 图形验证码请求间隔最小时间
    private static final int CAPTCHA_INTERVAL_SECONDS = 1;

    @Resource
    private CaptchaDto captchaDto;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成图像验证码
     *
     * @return
     */
    @Override
    public CaptchaVo generateCaptcha() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(captchaDto.getWidth(), captchaDto.getHeight(), captchaDto.getCount(),
                captchaDto.getLineCount());
        String uuid = IdUtil.randomUUID();
        String genUrl = captchaDto.getUrl() + uuid + ".png";
        lineCaptcha.write(genUrl);
        // 获取生成的验证码
        String captchaCode = lineCaptcha.getCode();
        // 存入redis中
        String redisKey = "captcha:code:" + uuid;
        // 存5min
        stringRedisTemplate.opsForValue().set(redisKey, captchaCode, Duration.ofMinutes(5));
        // 返回CaptchaVo
        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setUuid(uuid);
        captchaVo.setCodeUrl(genUrl);
        return captchaVo;
    }

    /**
     * 检查验证码
     * @return
     */
    @Override
    public boolean checkCaptcha(String captchaCode, String uuid) {
        // 1.从 Redis 获取验证码
        String redisKey = "captcha:code:" + uuid;
        String redisCode = stringRedisTemplate.opsForValue().get(redisKey);
        // 2.校验
        // 验证码过期
        ThrowUtils.throwIf(redisCode.isEmpty(), ErrorCode.CAPTCHA_EXPIRED);
        // 3.判断是否一致（忽略大小写）
        ThrowUtils.throwIf(!redisCode.equalsIgnoreCase(captchaCode), ErrorCode.CAPTCHA_ERROR);
        // 4.验证成功后删除（防止重复使用）
        stringRedisTemplate.delete(redisKey);
        return true;
    }
}




