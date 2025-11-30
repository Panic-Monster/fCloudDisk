package com.fujimao.fclouddisk.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.common.ResultUtils;
import com.fujimao.fclouddisk.entity.query.UserInfo;
import com.fujimao.fclouddisk.entity.vo.UserInfoVo;
import com.fujimao.fclouddisk.entity.vo.UserLoginVo;
import com.fujimao.fclouddisk.service.UserInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/login")
    public BaseResponse<UserInfoVo> login(@RequestBody UserLoginVo userLoginVo) {
        return userInfoService.doLogin(userLoginVo);
    }
}
