package com.fujimao.fclouddisk.service;

import com.fujimao.fclouddisk.common.BaseResponse;
import com.fujimao.fclouddisk.entity.query.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fujimao.fclouddisk.entity.vo.UserLoginVo;

/**
* @author Administrator
* @description 针对表【user_info(用户信息表)】的数据库操作Service
* @createDate 2025-11-29 09:53:19
*/
public interface UserInfoService extends IService<UserInfo> {
    BaseResponse<UserInfo> doLogin(UserLoginVo userLoginVo);
}
