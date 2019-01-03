
CREATE TABLE `activity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '活动名称',
  `type` smallint(4) unsigned NOT NULL COMMENT '活动类型',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `offline_time` datetime NOT NULL COMMENT '活动截止时间, 如无特殊需求与end_time保持一致',
  `config` varchar(512) NOT NULL DEFAULT '' COMMENT '活动配置信息(JSON格式)',
  `status` tinyint(2) NOT NULL DEFAULT 1 COMMENT '活动状态, 1:未发布, 2:进行中,3:已下线',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL COMMENT '修改日期',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_type` (`type`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动配置表';

CREATE TABLE `activity_award` (
 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_id` bigint(11) NOT NULL COMMENT '活动id',
  `name` varchar(128) NOT NULL COMMENT '奖品名称',
  `type` smallint(4) unsigned NOT NULL COMMENT '奖品类型, 1:红包, 2:实物, 3:虚拟充值卡',
  `sub_type` smallint(4) unsigned NOT NULL DEFAULT 1 COMMENT '奖品扩展子类型',
  `actual_price` varchar(12) NOT NULL DEFAULT '' COMMENT '奖品真实价格',
  `exchange_price` varchar(12) NOT NULL DEFAULT '' COMMENT '奖品兑换价格',
  `pic_url` varchar(255) NOT NULL DEFAULT '' COMMENT '奖品图片URL',
  `total_stock` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '奖品库存数量',
  `position` int(10) NOT NULL DEFAULT 0 COMMENT '奖品展示的位置',
  `crm_catalog_id` varchar(20) NOT NULL DEFAULT '' COMMENT 'crm系统奖品类目',
  `visible` smallint(4) NOT NULL DEFAULT 1 COMMENT '中奖动态中是否展示, 1: 展示, 2:不展示',
  `status` smallint(4) NOT NULL DEFAULT 1 COMMENT '奖品状态, 1:正常, 2:已删除',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL COMMENT '修改日期',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_activity_id` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='奖品配置表';

CREATE TABLE `activity_user_award` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(40) NOT NULL COMMENT '用户昵称',
  `award_id` bigint(20) NOT NULL COMMENT '奖品ID',
  `award_name` varchar(255) NOT NULL COMMENT '奖品名称',
  `award_type` smallint(4) unsigned NOT NULL COMMENT '奖品类型, 1:红包, 2:实物, 3:虚拟充值卡',
  `award_price` varchar(20) NOT NULL DEFAULT '' COMMENT '奖品单价',
  `quantity` int(11) NOT NULL DEFAULT 1 COMMENT '奖品数量',
  `visible` smallint(4) NOT NULL DEFAULT 1 COMMENT '中奖动态中是否展示, 1: 展示, 2:不展示',
  `receiver_name` varchar(40) NOT NULL DEFAULT '' COMMENT '收件人姓名',
  `receiver_mobile` varchar(40) NOT NULL DEFAULT '' COMMENT '收件人手机号',
  `receiver_address` varchar(255) NOT NULL DEFAULT '' COMMENT '邮寄地址',
  `status` smallint(4) NOT NULL COMMENT '奖品状态, 1:待领取, 2:已领取, 3:作废',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL COMMENT '修改日期',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_activity_id` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户奖品表';
