package com.fujimao.fclouddisk.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文件信息表
 * @TableName file_info
 */
@TableName(value ="file_info")
@Data
public class FileInfo implements Serializable {
    /**
     * 文件ID
     */
    @TableId(value = "file_id")
    private String fileId;

    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private String userId;

    /**
     * 文件MD5值
     */
    @TableField(value = "file_md5")
    private String fileMd5;

    /**
     * 父级ID
     */
    @TableField(value = "file_pid")
    private String filePid;

    /**
     * 文件大小（单位byte）
     */
    @TableField(value = "file_size")
    private Long fileSize;

    /**
     * 文件名
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 文件封面
     */
    @TableField(value = "file_cover")
    private String fileCover;

    /**
     * 文件路径
     */
    @TableField(value = "file_path")
    private String filePath;

    /**
     * 0文件 1目录
     */
    @TableField(value = "folder_type")
    private Integer folderType;

    /**
     * 文件分类（1视频 2音频 3图片 4文档 5其他）
     */
    @TableField(value = "file_category")
    private Integer fileCategory;

    /**
     * 文件细节分类（1:视频 2:音频 3:图片 4:pdf 5:doc 6:excel 7:txt 8:code 9:zip 5:其他）
     */
    @TableField(value = "file_type")
    private Integer fileType;

    /**
     * 状态（0:转码中 1:转码失败 2:转码成功）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除（0删除 1恢复）
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 回收站时间
     */
    @TableField(value = "recovery_time")
    private Date recoveryTime;

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
        FileInfo other = (FileInfo) that;
        return (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getFileMd5() == null ? other.getFileMd5() == null : this.getFileMd5().equals(other.getFileMd5()))
            && (this.getFilePid() == null ? other.getFilePid() == null : this.getFilePid().equals(other.getFilePid()))
            && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getFileCover() == null ? other.getFileCover() == null : this.getFileCover().equals(other.getFileCover()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getFolderType() == null ? other.getFolderType() == null : this.getFolderType().equals(other.getFolderType()))
            && (this.getFileCategory() == null ? other.getFileCategory() == null : this.getFileCategory().equals(other.getFileCategory()))
            && (this.getFileType() == null ? other.getFileType() == null : this.getFileType().equals(other.getFileType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getRecoveryTime() == null ? other.getRecoveryTime() == null : this.getRecoveryTime().equals(other.getRecoveryTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFileMd5() == null) ? 0 : getFileMd5().hashCode());
        result = prime * result + ((getFilePid() == null) ? 0 : getFilePid().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFileCover() == null) ? 0 : getFileCover().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getFolderType() == null) ? 0 : getFolderType().hashCode());
        result = prime * result + ((getFileCategory() == null) ? 0 : getFileCategory().hashCode());
        result = prime * result + ((getFileType() == null) ? 0 : getFileType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getRecoveryTime() == null) ? 0 : getRecoveryTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileId=").append(fileId);
        sb.append(", userId=").append(userId);
        sb.append(", fileMd5=").append(fileMd5);
        sb.append(", filePid=").append(filePid);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileCover=").append(fileCover);
        sb.append(", filePath=").append(filePath);
        sb.append(", folderType=").append(folderType);
        sb.append(", fileCategory=").append(fileCategory);
        sb.append(", fileType=").append(fileType);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", recoveryTime=").append(recoveryTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}