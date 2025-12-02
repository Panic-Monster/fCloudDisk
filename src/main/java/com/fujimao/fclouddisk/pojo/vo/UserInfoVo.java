package com.fujimao.fclouddisk.pojo.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfoVo implements Serializable {


    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq_open_id
     */
    private String qqOpenId;

    /**
     * qq头像
     */
    private String qqAvatar;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间（包括用户信息和最后登录时间）
     */
    private Date updateTime;

    /**
     * 使用空间单位 byte
     */
    private Long useSpace;

    /**
     * 总空间
     */
    private Long totalSpace;

    @Serial
    private static final long serialVersionUID = 7943589426608854637L;
}
