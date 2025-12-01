package com.fujimao.fclouddisk.controller;

import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.common.ResultUtils;
import com.fujimao.fclouddisk.entity.vo.UserInfoVo;
import com.fujimao.fclouddisk.entity.vo.UserLoginVo;
import com.fujimao.fclouddisk.service.UserInfoService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 登录
     * @param userLoginVo
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<UserInfoVo> login(@RequestBody UserLoginVo userLoginVo, HttpSession session) {
        UserInfoVo userInfoVo = userInfoService.doLogin(userLoginVo);
        session.setAttribute("userInfoVo", userInfoVo);
        return ResultUtils.success(userInfoVo);
    }

    /**
     * 注册
     * @param userLoginVo
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<UserInfoVo> register(@RequestBody UserLoginVo userLoginVo) {
        // UserInfoVo userInfoVo = userInfoService.doRegister(userLoginVo);
        return ResultUtils.success(null);
    }
}
