package com.fujimao.fclouddisk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.common.ErrorCode;
import com.fujimao.fclouddisk.common.ResultUtils;
import com.fujimao.fclouddisk.exception.ThrowUtils;
import com.fujimao.fclouddisk.pojo.entity.UserInfo;
import com.fujimao.fclouddisk.pojo.vo.UserInfoVo;
import com.fujimao.fclouddisk.pojo.vo.UserLoginVo;
import com.fujimao.fclouddisk.pojo.vo.UserRegisterVo;
import com.fujimao.fclouddisk.service.CaptchaService;
import com.fujimao.fclouddisk.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.jdbc.Null;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@Tag(name = "用户接口", description = "用户相关操作，如登录注册")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private CaptchaService captchaService;

    /**
     * 登录
     * @param userLoginVo 用户登录实体
     * @return 返回 sessionId
     */
    @Operation(summary = "用户邮箱登录", description = "通过邮箱密码进行登录")
    @PostMapping("/login")
    public BaseResponse<String> login(
            @Schema(description = "用户登录实体")
            @RequestBody UserLoginVo userLoginVo,
            HttpSession session) {
        UserInfoVo userInfoVo = userInfoService.doLogin(userLoginVo);
        session.setAttribute("userInfoVo", userInfoVo);
        String sessionId = session.getId();
        return ResultUtils.success(sessionId);
    }

    /**
     * 退出登录
     */
    @Operation(summary = "退出登录", description = "退出登录")
    @GetMapping("/logout")
    public BaseResponse<Void> logout(HttpSession session) {
        session.invalidate();
        return ResultUtils.success(null, "退出成功");
    }

    /**
     * 注册
     * @param userRegisterVo 用户登录实体
     */
    @Operation(summary = "用户邮箱注册", description = "通过邮箱和验证码进行注册")
    @PostMapping("/register")
    public BaseResponse<UserInfoVo> register(@RequestBody UserRegisterVo userRegisterVo) {
        Boolean userInfoVo = userInfoService.doRegister(userRegisterVo);
        return ResultUtils.success(null);
    }

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @Operation(summary = "获取用户信息", description = "用sessionId来拿用户信息")
    @PostMapping("/info/get")
    public BaseResponse<UserInfoVo> getUserInfo(HttpSession session) {
        UserInfoVo userInfoVo = (UserInfoVo) session.getAttribute("userInfoVo");
        ThrowUtils.throwIf(userInfoVo == null, ErrorCode.NO_LOGIN);
        return ResultUtils.success(userInfoVo);
    }

    /**
     * 获取用户空间
     * @param userId
     * @return
     */
    @Operation(summary = "获取用户空间", description = "用userId来拿用户存储空间")
    @GetMapping("/space/get")
    public BaseResponse<Long> getUserSpace(@RequestParam String userId) {
        UserInfo userInfo = userInfoService.getById(userId);
        ThrowUtils.throwIf(userInfo == null, ErrorCode.DATA_NOT_FOUND);
        return ResultUtils.success(userInfo.getUseSpace());
    }

    /**
     * 获取用户头像
     * @param userId
     * @return
     */
    @Operation(summary = "获取用户头像", description = "用userId来获取用户头像")
    @GetMapping("/avatar/upload")
    public BaseResponse<String> getUserAvatar(@RequestParam String userId) {
        UserInfo userInfo = userInfoService.getById(userId);
        ThrowUtils.throwIf(userInfo == null, ErrorCode.DATA_NOT_FOUND);
        return ResultUtils.success(userInfo.getQqAvatar());
    }

    /**
     * 更新用户头像
     * @param userId
     * @return
     */
    @Operation(summary = "更新用户头像", description = "用userId来更新用户头像")
    @PostMapping("/avatar/update")
    public BaseResponse<String> updateUserAvatar(
            @RequestParam("file") MultipartFile file,
            @RequestParam String userId) throws Exception {
        // 先不用MinIO，先上传到磁盘
        String url = userInfoService.updateAvatar(file, userId);
        ThrowUtils.throwIf(url == null, ErrorCode.PARAM_ERROR);
        // userInfoService.updateAvatar(userId);
        // ThrowUtils.throwIf(userInfo == null, ErrorCode.DATA_NOT_FOUND);
        return ResultUtils.success(url);
    }

    /**
     * 修改密码
     * @return
     */
    @Operation(summary = "修改密码", description = "输入原密码和修改后密码进行修改")
    @Transactional
    @PostMapping("/password/change")
    public BaseResponse<Void> changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            HttpSession session) {
        Boolean result = userInfoService.changePassword(oldPassword, newPassword, confirmPassword, session.getId());
        ThrowUtils.throwIf(result, ErrorCode.DATA_DELETE_FAIL);
        return ResultUtils.success(null);
    }

    /**
     * 重置密码
     * @return
     */
    @Operation(summary = "重置密码", description = "通过邮件和验证码进行重置")
    @Transactional
    @PostMapping("/password/reset")
    public BaseResponse<Void> resetPassword(
            @RequestParam String email,
            @RequestParam String inputCode,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword) {
        // 验证验证码是否正确
        boolean resultValidateCode = captchaService.validateCode(email, inputCode);
        ThrowUtils.throwIf(resultValidateCode, ErrorCode.CAPTCHA_ERROR);
        // 修改密码
        Boolean resultResetPassword = userInfoService.resetPassword(newPassword, confirmPassword, email);
        ThrowUtils.throwIf(resultResetPassword, ErrorCode.CAPTCHA_ERROR);
        return ResultUtils.success(null);
    }
}
