package com.fujimao.fclouddisk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.common.ErrorCode;
import com.fujimao.fclouddisk.common.ResultUtils;
import com.fujimao.fclouddisk.exception.ThrowUtils;
import com.fujimao.fclouddisk.pojo.entity.UserInfo;
import com.fujimao.fclouddisk.pojo.vo.UserInfoVo;
import com.fujimao.fclouddisk.pojo.vo.UserLoginVo;
import com.fujimao.fclouddisk.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "用户接口", description = "用户相关操作，如登录注册")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 登录
     * @param userLoginVo
     * @return
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

    @Operation(summary = "退出登录", description = "退出登录")
    @GetMapping("/logout")
    public BaseResponse<Void> logout(HttpSession session) {
        session.invalidate();
        return ResultUtils.success(null, "退出成功");
    }

    /**
     * 注册
     * @param userLoginVo
     * @return
     */
    @Operation(summary = "用户邮箱注册", description = "通过邮箱和验证码进行注册")
    @PostMapping("/register")
    public BaseResponse<UserInfoVo> register(@RequestBody UserLoginVo userLoginVo) {
        // UserInfoVo userInfoVo = userInfoService.doRegister(userLoginVo);
        return ResultUtils.success(null);
    }

    @Operation(summary = "获取用户信息", description = "用sessionId来拿用户信息")
    @PostMapping("/getUserInfo")
    public BaseResponse<UserInfoVo> getUserInfo(HttpSession session) {
        UserInfoVo userInfoVo =(UserInfoVo) session.getAttribute("userInfoVo");
        ThrowUtils.throwIf(userInfoVo == null, ErrorCode.NO_LOGIN);
        return ResultUtils.success(userInfoVo);
    }

    @Operation(summary = "获取用户空间", description = "用userId来拿用户存储空间")
    @GetMapping("/getUserSpace")
    public BaseResponse<Long> getUserSpace(@RequestParam String userId) {
        UserInfo userInfo = userInfoService.getById(userId);
        ThrowUtils.throwIf(userInfo == null, ErrorCode.DATA_NOT_FOUND);
        return ResultUtils.success(userInfo.getUseSpace());
    }

    @Operation(summary = "获取用户头像", description = "用userId来拿用户头像")
    @GetMapping("/getUserAvatar")
    public BaseResponse<String> getUserAvatar(@RequestParam String userId) {
        UserInfo userInfo = userInfoService.getById(userId);
        ThrowUtils.throwIf(userInfo == null, ErrorCode.DATA_NOT_FOUND);
        return ResultUtils.success(userInfo.getQqAvatar());
    }
}
