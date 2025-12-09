/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : 122.51.104.122:3306
 Source Schema         : cloud_disk

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 09/12/2025 11:36:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `file_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件ID',
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `file_md5` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件MD5值',
  `file_pid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级ID',
  `file_size` bigint NULL DEFAULT NULL COMMENT '文件大小（单位byte）',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_cover` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件封面',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `folder_type` tinyint(1) NULL DEFAULT NULL COMMENT '0文件 1目录',
  `file_category` tinyint(1) NULL DEFAULT NULL COMMENT '文件分类（1视频 2音频 3图片 4文档 5其他）',
  `file_type` tinyint(1) NULL DEFAULT NULL COMMENT '文件细节分类（1:视频 2:音频 3:图片 4:pdf 5:doc 6:excel 7:txt 8:code 9:zip 5:其他）',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（0:转码中 1:转码失败 2:转码成功）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除（0删除 1恢复）',
  `recovery_time` datetime NULL DEFAULT NULL COMMENT '回收站时间',
  PRIMARY KEY (`file_id`, `user_id`) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_md5`(`file_md5` ASC) USING BTREE,
  INDEX `idx_file_pid`(`file_pid` ASC) USING BTREE,
  INDEX `idx_is_delete`(`is_delete` ASC) USING BTREE,
  INDEX `idx_recovery_time`(`recovery_time` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `qq_open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'qq_open_id',
  `qq_avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'qq头像',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（包括用户信息和最后登录时间）',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（0禁用 1启用）',
  `is_delete` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除（0删除 1恢复）',
  `use_space` bigint NULL DEFAULT 0 COMMENT '使用空间单位 byte',
  `total_space` bigint NULL DEFAULT 2147483648 COMMENT '总空间（默认2GB）',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `key_email`(`email` ASC) USING BTREE,
  UNIQUE INDEX `key_qq_open_id`(`qq_open_id` ASC) USING BTREE,
  UNIQUE INDEX `key_nick_name`(`nick_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('79624572e40978078bfe8ef51d01d3a8', '小小呆', '1459124289@qq.com', NULL, NULL, '0192023a7bbd73250516f069df18b500', '2025-12-08 09:56:39', '2025-12-08 09:56:39', 1, 1, 0, 2147483648);
INSERT INTO `user_info` VALUES ('admin001', '超级管理员', 'admin@example.com', 'adminqqid001', 'https://example.com/avatar1.jpg', '0192023a7bbd73250516f069df18b500', '2025-11-30 09:56:39', '2025-11-30 10:03:58', 1, 1, 0, 1048576000);
INSERT INTO `user_info` VALUES ('user001', '小明', 'xiaoming@example.com', 'qqid12345', 'https://example.com/avatar2.jpg', 'e10adc3949ba59abbe56e057f20f883e', '2025-11-30 09:56:40', '2025-11-30 09:56:40', 1, 1, 524288000, 1073741824);
INSERT INTO `user_info` VALUES ('user002', '小红', 'xiaohong@example.com', 'qqid67890', 'https://example.com/avatar3.jpg', 'e10adc3949ba59abbe56e057f20f883e', '2025-11-30 09:56:40', '2025-11-30 09:56:40', 1, 1, 104857600, 2097152000);
INSERT INTO `user_info` VALUES ('user003', '李雷', 'lilei@example.com', 'qqid11223', 'https://example.com/avatar4.jpg', 'e10adc3949ba59abbe56e057f20f883e', '2025-11-30 09:56:40', '2025-11-30 09:56:40', 0, 1, 0, 524288000);
INSERT INTO `user_info` VALUES ('user004', '王芳', 'wangfang@example.com', 'qqid33445', 'https://example.com/avatar5.jpg', 'e10adc3949ba59abbe56e057f20f883e', '2025-11-30 09:56:40', '2025-11-30 09:56:40', 1, 0, 256000000, 1048576000);

SET FOREIGN_KEY_CHECKS = 1;
