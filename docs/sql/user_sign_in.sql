
CREATE TABLE `user_sign_in` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `nickname` varchar(45) NOT NULL COMMENT '用户昵称',
  `last_sign_in_date` int(10) unsigned NOT NULL COMMENT '最近一次签到日期，格式: yyyyMMdd',
  `continuous_sign_in_days` int(10) unsigned NOT NULL COMMENT '连续签到天数',
  `version` int(10) unsigned NOT NULL DEFAULT 1 COMMENT '版本号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户签到表';

CREATE TABLE `user_sign_in_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `sign_in_date` int(10) unsigned NOT NULL COMMENT '签到日期，格式: yyyyMMdd',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_user_id_sign_date` (`user_id`, `sign_in_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户签到log表';