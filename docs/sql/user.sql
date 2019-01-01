
CREATE DATABASE `system_design_notes` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `system_design_notes`;

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile` varchar(40) NOT NULL  COMMENT '用户注册手机号',
  `nickname` varchar(40) NOT NULL DEFAULT ''  COMMENT '用户昵称',
  `login_password` varchar(64) NOT NULL DEFAULT ''  COMMENT '用户登录手机号',
  `head_img_url` varchar(128) NOT NULL DEFAULT '' COMMENT '用户图像URL',
  `id_verified` tinyint(2) unsigned NOT NULL DEFAULT 1 COMMENT '用户实名状态, 1:未实名, 2:实名认证失败, 3:已实名',
  `real_name` varchar(40) NOT NULL DEFAULT ''  COMMENT '用户昵称',
  `id_card_type` tinyint(2) unsigned NOT NULL DEFAULT 0 COMMENT '用户实名类型, 0:未实名, 1:身份证, 2:护照',
  `id_card_no` varchar(40) NOT NULL DEFAULT ''  COMMENT '用户实名证件号',
  `status` tinyint(2) unsigned NOT NULL DEFAULT 1 COMMENT '账号状态, 1:正常, 2:冻结',
  `utm_source` varchar(40) NOT NULL DEFAULT ''  COMMENT '用户注册渠道',
  `inviter_user_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '邀请好友用户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT 'version',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

CREATE TABLE `user_operation_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `operation_type` tinyint(2) unsigned NOT NULL DEFAULT 1 COMMENT '用户操作类型, 1:启动打卡, 2:用户登录, 3:退出登录',
  `operation_desc` varchar(40) NOT NULL DEFAULT ''  COMMENT '操作描述',
  `lat` varchar(12) NOT NULL DEFAULT ''  COMMENT '用户当前所在位置lat',
  `lng` varchar(12) NOT NULL DEFAULT ''  COMMENT '用户当前所在位置lng',
  `client_type` tinyint(2) unsigned NOT NULL DEFAULT 1 COMMENT '客户端类型, 1:App, 2:PC, 3:H5',
  `device_name` varchar(45) NOT NULL DEFAULT ''  COMMENT '设备名称',
  `os_name` varchar(20) NOT NULL DEFAULT ''  COMMENT '操作系统name',
  `os_version` varchar(12) NOT NULL DEFAULT ''  COMMENT '操作系统版本',
  `app_version` varchar(12) NOT NULL DEFAULT ''  COMMENT 'app版本',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT 'version',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户操作log表';