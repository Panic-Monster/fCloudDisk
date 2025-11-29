package com.fujimao.fclouddisk.controller;

import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.common.ResultUtils;
import com.fujimao.fclouddisk.entity.query.UserInfo;
import com.fujimao.fclouddisk.entity.vo.UserLoginVo;
import com.fujimao.fclouddisk.service.UserInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public BaseResponse<UserInfo> login(@RequestBody UserLoginVo userLoginVo) {
        return userInfoService.doLogin(userLoginVo);
    }
}
