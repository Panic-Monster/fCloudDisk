package com.fujimao.fclouddisk.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.fujimao.fclouddisk.common.ErrorCode;
import com.fujimao.fclouddisk.pojo.dto.CaptchaDto;
import com.fujimao.fclouddisk.exception.ThrowUtils;
import com.fujimao.fclouddisk.pojo.vo.CaptchaVo;
import com.fujimao.fclouddisk.service.CaptchaService;
import com.fujimao.fclouddisk.utils.CodeUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

/**
 * @author Administrator
 * @description 针对【账号相关】的数据库操作Service实现
 * @createDate 2025-11-29 09:53:19
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    private CaptchaDto captchaDto;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.mail.username}")
    private String fromMail;

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
     * 检查图形验证码
     *
     * @return
     */
    @Override
    public boolean checkCaptcha(String captchaCode, String uuid) {
        // 1.从 Redis 获取验证码
        String redisKey = "captcha:code:" + uuid;
        String redisCode = stringRedisTemplate.opsForValue().get(redisKey);
        // 2.校验
        // 判空
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(captchaCode), ErrorCode.NULL_ERROR);
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(uuid), ErrorCode.NULL_ERROR);
        // 验证码过期
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(redisCode), ErrorCode.CAPTCHA_EXPIRED);
        // 3.判断是否一致（忽略大小写）
        ThrowUtils.throwIf(!CharSequenceUtil.equalsIgnoreCase(redisCode, captchaCode), ErrorCode.CAPTCHA_ERROR);
        // 4.验证成功后删除（防止重复使用）
        stringRedisTemplate.delete(redisKey);
        return true;
    }

    /**
     * 通过邮箱发送邮件
     *
     * @param email
     * @return
     */
    @Override
    public boolean sendEmailForCaptcha(String email) {
        // 生成验证码
        String code = CodeUtil.generateCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(email);
        message.setSubject("【腹肌猫网盘系统】您的验证码");
        message.setText("您的验证码是：" + code + "，有效期 5 分钟，请勿泄露。");
        stringRedisTemplate.opsForValue().set("verify:email:" + email, code, Duration.ofMinutes(5));
        mailSender.send(message);
        return false;
    }

    /**
     * 验证邮箱发送的验证码
     *
     * @param email
     * @return
     */
    public boolean validateCode(String email, String inputCode) {
        String redisKey = "verify:email:" + email;
        String code = stringRedisTemplate.opsForValue().get(redisKey);
        // 校验
        // 判空
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(email), ErrorCode.NULL_ERROR);
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(inputCode), ErrorCode.NULL_ERROR);
        // 验证码过期
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(code), ErrorCode.CAPTCHA_EXPIRED);
        // 验证是否错误
        ThrowUtils.throwIf(!CharSequenceUtil.equalsIgnoreCase(code, inputCode), ErrorCode.CAPTCHA_ERROR);
        // 验证成功后删除
        stringRedisTemplate.delete(redisKey);
        return true;
    }
}




