package com.fujimao.fclouddisk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fujimao.fclouddisk.common.ErrorCode;
import com.fujimao.fclouddisk.pojo.entity.UserInfo;
import com.fujimao.fclouddisk.pojo.vo.UserInfoVo;
import com.fujimao.fclouddisk.pojo.vo.UserLoginVo;
import com.fujimao.fclouddisk.exception.BusinessException;
import com.fujimao.fclouddisk.service.CaptchaService;
import com.fujimao.fclouddisk.service.UserInfoService;
import com.fujimao.fclouddisk.mappers.UserInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
* @createDate 2025-11-29 09:53:19
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private CaptchaService captchaService;

    /**
     * 登录
     * @param userLoginVo 用户登录
     * @return
     */
    public UserInfoVo doLogin(UserLoginVo userLoginVo) {
        String email = userLoginVo.getEmail();
        String password = userLoginVo.getPassword();
        // 校验邮箱是否存在
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.eq("email", email);
        UserInfo loginUser = userInfoMapper.selectOne(userInfoQueryWrapper);
        if (loginUser == null) {
            // 该邮箱还未注册，请先注册
            throw new BusinessException(ErrorCode.NO_REGISTER);
        }
        // 验证图形验证码
        boolean checkResult = captchaService.checkCaptcha(userLoginVo.getCaptchaCode(), userLoginVo.getVerifyUuid());
        if (!checkResult) {
            throw new BusinessException(ErrorCode.CAPTCHA_ERROR);
        }
        // 处理明文密码为MD5
        String encryptedPassword = DigestUtil.md5Hex(password);
        if (!encryptedPassword.equals(loginUser.getPassword())) {
            // 密码错误
            throw new BusinessException(ErrorCode.PASSWORD_ERROR);
        }

        // 数据脱敏
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtil.copyProperties(loginUser, userInfoVo);
        return userInfoVo;
    }
}




