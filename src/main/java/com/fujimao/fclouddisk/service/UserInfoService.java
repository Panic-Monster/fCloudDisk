package com.fujimao.fclouddisk.service;

import com.fujimao.fclouddisk.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fujimao.fclouddisk.pojo.vo.UserInfoVo;
import com.fujimao.fclouddisk.pojo.vo.UserLoginVo;
import com.fujimao.fclouddisk.pojo.vo.UserRegisterVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    /**
     * 注册
     * @param userRegisterVo 用户注册
     * @return
     */
    Boolean doRegister(UserRegisterVo userRegisterVo);

    /**
     * 修改密码
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param confirmPassword 密码确认
     * @param userId 用户id
     * @return
     */
    Boolean changePassword(String oldPassword, String newPassword, String confirmPassword, String userId);

    /**
     * 重置密码
     * @param newPassword 新密码
     * @param confirmPassword 密码确认
     * @param email 用户邮箱
     * @return
     */
    Boolean resetPassword(String newPassword, String confirmPassword, String email);

    /**
     * 更新用户头像
     * @param userId
     * @return
     */
    String updateAvatar(MultipartFile file, String userId) throws IOException;
}
