package com.fujimao.fclouddisk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fujimao.fclouddisk.common.ErrorCode;
import com.fujimao.fclouddisk.exception.ThrowUtils;
import com.fujimao.fclouddisk.pojo.entity.UserInfo;
import com.fujimao.fclouddisk.pojo.vo.UserInfoVo;
import com.fujimao.fclouddisk.pojo.vo.UserLoginVo;
import com.fujimao.fclouddisk.exception.BusinessException;
import com.fujimao.fclouddisk.pojo.vo.UserRegisterVo;
import com.fujimao.fclouddisk.service.CaptchaService;
import com.fujimao.fclouddisk.service.UserInfoService;
import com.fujimao.fclouddisk.mappers.UserInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    @Override
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

    /**
     * 注册
     * @param userRegisterVo 用户注册
     * @return
     */
    @Override
    @Transactional
    public Boolean doRegister(UserRegisterVo userRegisterVo) {
        // 解构
        String email = userRegisterVo.getEmail();
        String emailCode = userRegisterVo.getEmailCode();
        String password = userRegisterVo.getPassword();
        String verificationPassword = userRegisterVo.getVerificationPassword();
        String nickName = userRegisterVo.getNickName();
        // 校验
        // 判空
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(email), ErrorCode.NULL_ERROR);
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(emailCode), ErrorCode.NULL_ERROR);
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(password), ErrorCode.NULL_ERROR);
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(verificationPassword), ErrorCode.NULL_ERROR);
        ThrowUtils.throwIf(StrUtil.isEmptyIfStr(nickName), ErrorCode.NULL_ERROR);
        // 先判断邮箱验证码是否正确
        boolean result = captchaService.validateCode(email, emailCode);
        ThrowUtils.throwIf(!result, ErrorCode.CAPTCHA_ERROR);
        // 判断密码和二次验证密码是否一致
        ThrowUtils.throwIf(!CharSequenceUtil.equals(password, verificationPassword), ErrorCode.PARAM_ERROR, "两次密码不一致");
        // 给密码加密
        String encryptedPassword = DigestUtil.md5Hex(password);
        // 写入数据库
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setNickName(nickName);
        userInfo.setPassword(encryptedPassword);
        int insert = userInfoMapper.insert(userInfo);
        ThrowUtils.throwIf(insert != 1, ErrorCode.DATA_SAVE_FAIL);
        return true;
    }

    /**
     * 修改密码
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param confirmPassword 密码确认
     * @param userId 用户id
     * @return
     */
    @Override
    public Boolean changePassword(String oldPassword, String newPassword, String confirmPassword, String userId) {
        ThrowUtils.throwIf(newPassword.equals(confirmPassword), ErrorCode.PARAM_ERROR, "两次密码不一致");
        // 先将密码MD5加密
        String encryptedOldPassword = SecureUtil.md5(oldPassword);
        UserInfo userInfo = userInfoMapper.selectById(userId);
        // 没查到返回参数错误
        ThrowUtils.throwIf(userInfo == null, ErrorCode.DATA_NOT_FOUND);
        // 比较密码，不一致返回原密码错误
        ThrowUtils.throwIf(userInfo.getPassword().equals(encryptedOldPassword), ErrorCode.PARAM_ERROR, "旧密码错误");
        // 修改密码
        //1.加密新密码
        String encryptedNewPassword = SecureUtil.md5(oldPassword);
        // 2.添加数据库
        UpdateWrapper<UserInfo> userInfoUpdateWrapper = new UpdateWrapper<>();
        userInfoUpdateWrapper.set("password", encryptedNewPassword).eq("user_id", userId);
        int update = userInfoMapper.update(userInfoUpdateWrapper);
        // 返回影响数据条数(不唯一修改失败)
        ThrowUtils.throwIf(update != 1, ErrorCode.DATA_UPDATE_FAIL, "修改密码失败");
        return true;
    }

    /**
     * 重置密码
     * @param newPassword 新密码
     * @param confirmPassword 密码确认
     * @param email 用户邮箱
     * @return
     */
    @Override
    @Transactional
    public Boolean resetPassword(String newPassword, String confirmPassword, String email) {
        // 校验
        ThrowUtils.throwIf(newPassword.equals(confirmPassword), ErrorCode.PARAM_ERROR, "两次密码不一致");
        // 根据email修改用户
        UpdateWrapper<UserInfo> userInfoUpdateWrapper = new UpdateWrapper<>();
        userInfoUpdateWrapper.set("password", SecureUtil.md5(newPassword))
                .eq("email", email);
        int update = userInfoMapper.update(userInfoUpdateWrapper);
        // 影响条数不为1则抛异常
        ThrowUtils.throwIf(update != 1, ErrorCode.DATA_UPDATE_FAIL);
        return true;
    }

    /**
     * 更新用户头像
     * @param userId
     * @return
     */
    @Override
    public String updateAvatar(MultipartFile file, String userId) throws IOException {
        // 更新用户头像
        ThrowUtils.throwIf(file.isEmpty(), ErrorCode.PARAM_ERROR);
        // 保存到本地
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String path = "D:/yupi_project/fCloudDisk/src/main/resources/avatar/" + filename;
        file.transferTo(new File(path));

        // 返回访问 URL（本地测试用）
        String url = "http://localhost:7529/upload/" + filename;

        // 存入数据库 qq_avatar 字段
        UpdateWrapper<UserInfo> userInfoUpdateWrapper = new UpdateWrapper<>();
        userInfoUpdateWrapper.set("qq_avatar", url)
                .eq("user_id", userId);
        int update = userInfoMapper.update(userInfoUpdateWrapper);
        ThrowUtils.throwIf(update != 1, ErrorCode.DATA_UPDATE_FAIL);
        return url;
    }
}




