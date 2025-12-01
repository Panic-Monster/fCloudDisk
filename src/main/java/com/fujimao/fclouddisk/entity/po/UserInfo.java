package com.fujimao.fclouddisk.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息表
 * @ TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 用户ID
     */
    @TableId
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
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间（包括用户信息和最后登录时间）
     */
    private Date updateTime;

    /**
     * 状态（0禁用 1启用）
     */
    private Integer status;

    /**
     * 是否删除（0删除 1恢复）
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 使用空间单位 byte
     */
    private Long useSpace;

    /**
     * 总空间
     */
    private Long totalSpace;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserInfo other = (UserInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getQqOpenId() == null ? other.getQqOpenId() == null : this.getQqOpenId().equals(other.getQqOpenId()))
            && (this.getQqAvatar() == null ? other.getQqAvatar() == null : this.getQqAvatar().equals(other.getQqAvatar()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getUseSpace() == null ? other.getUseSpace() == null : this.getUseSpace().equals(other.getUseSpace()))
            && (this.getTotalSpace() == null ? other.getTotalSpace() == null : this.getTotalSpace().equals(other.getTotalSpace()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getQqOpenId() == null) ? 0 : getQqOpenId().hashCode());
        result = prime * result + ((getQqAvatar() == null) ? 0 : getQqAvatar().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getUseSpace() == null) ? 0 : getUseSpace().hashCode());
        result = prime * result + ((getTotalSpace() == null) ? 0 : getTotalSpace().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", nickName=").append(nickName);
        sb.append(", email=").append(email);
        sb.append(", qqOpenId=").append(qqOpenId);
        sb.append(", qqAvatar=").append(qqAvatar);
        sb.append(", password=").append(password);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", useSpace=").append(useSpace);
        sb.append(", totalSpace=").append(totalSpace);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}