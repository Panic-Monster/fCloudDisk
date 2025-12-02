package com.fujimao.fclouddisk.service;

import com.fujimao.fclouddisk.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fujimao.fclouddisk.pojo.vo.UserInfoVo;
import com.fujimao.fclouddisk.pojo.vo.UserLoginVo;

/**
* @author Administrator
* @description 针对表【user_info(用户信息表)】的数据库操作Service
* @createDate 2025-11-29 09:53:19
*/
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 登录
     * @param userLoginVo 用户登录
     * @return
     */
    UserInfoVo doLogin(UserLoginVo userLoginVo);
}
