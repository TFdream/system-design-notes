
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
  `id_card_type` tinyint(2) unsigned NOT NULL DEFAULT 1 COMMENT '用户实名类型, 1:身份证, 2:护照',
  `id_card_no` varchar(40) NOT NULL DEFAULT ''  COMMENT '用户实名证件号',
  `status` tinyint(2) unsigned NOT NULL DEFAULT 1 COMMENT '账号状态, 1:正常, 2:冻结',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT 'version',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';