-- user 用户表
create table cloud_disk.user_info
(
    user_id     varchar(64)          not null comment '用户ID'
        primary key,
    nick_name   varchar(20)          null comment '用户昵称',
    email       varchar(128)         null comment '邮箱',
    qq_open_id  varchar(64)          null comment 'qq_open_id',
    qq_avatar   varchar(256)         null comment 'qq头像',
    password    varchar(32)          null comment '密码',
    create_time datetime             null comment '创建时间',
    update_time datetime             null on update CURRENT_TIMESTAMP comment '更新时间（包括用户信息和最后登录时间）',
    status      tinyint(1) default 1 null comment '状态（0禁用 1启用）',
    is_delete   tinyint(1) default 1 null comment '是否删除（0删除 1恢复）',
    use_space   bigint               null comment '使用空间单位 byte',
    total_space bigint               null comment '总空间',
    constraint key_email
        unique (email),
    constraint key_nick_name
        unique (nick_name),
    constraint key_qq_open_id
        unique (qq_open_id)
)
    comment '用户信息表';
-- 添加测试数据
-- 插入管理员用户
INSERT INTO cloud_disk.user_info (user_id, nick_name, email, qq_open_id, qq_avatar, password, create_time, update_time, status, is_delete, use_space, total_space)
VALUES
    ('admin001', '超级管理员', 'admin@example.com', 'adminqqid001', 'https://example.com/avatar1.jpg', 'e99a18c428cb38d5f260853678922e03', NOW(), NOW(), 1, 1, 0, 1048576000); -- 1GB total space

-- 插入普通用户1
INSERT INTO cloud_disk.user_info (user_id, nick_name, email, qq_open_id, qq_avatar, password, create_time, update_time, status, is_delete, use_space, total_space)
VALUES
    ('user001', '小明', 'xiaoming@example.com', 'qqid12345', 'https://example.com/avatar2.jpg', 'e10adc3949ba59abbe56e057f20f883e', NOW(), NOW(), 1, 1, 524288000, 1073741824); -- 500MB used, 1GB total space

-- 插入普通用户2
INSERT INTO cloud_disk.user_info (user_id, nick_name, email, qq_open_id, qq_avatar, password, create_time, update_time, status, is_delete, use_space, total_space)
VALUES
    ('user002', '小红', 'xiaohong@example.com', 'qqid67890', 'https://example.com/avatar3.jpg', 'e10adc3949ba59abbe56e057f20f883e', NOW(), NOW(), 1, 1, 104857600, 2097152000); -- 100MB used, 2GB total space

-- 插入被禁用的用户
INSERT INTO cloud_disk.user_info (user_id, nick_name, email, qq_open_id, qq_avatar, password, create_time, update_time, status, is_delete, use_space, total_space)
VALUES
    ('user003', '李雷', 'lilei@example.com', 'qqid11223', 'https://example.com/avatar4.jpg', 'e10adc3949ba59abbe56e057f20f883e', NOW(), NOW(), 0, 1, 0, 524288000); -- 0MB used, 500MB total space

-- 插入已删除的用户
INSERT INTO cloud_disk.user_info (user_id, nick_name, email, qq_open_id, qq_avatar, password, create_time, update_time, status, is_delete, use_space, total_space)
VALUES
    ('user004', '王芳', 'wangfang@example.com', 'qqid33445', 'https://example.com/avatar5.jpg', 'e10adc3949ba59abbe56e057f20f883e', NOW(), NOW(), 1, 0, 256000000, 1048576000); -- 256MB used, 1GB total space

