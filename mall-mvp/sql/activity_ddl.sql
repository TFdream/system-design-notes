## 营销活动

CREATE TABLE `shop_activity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '活动名称',
  `type` smallint(4) NOT NULL  COMMENT '活动类型 1:积分抵扣活动 2:拼团 3:秒杀',
  `start_time` timestamp NOT NULL COMMENT '活动开始时间',
  `end_time` timestamp NOT NULL COMMENT '活动结束时间',
  `rules` varchar(2000) NOT NULL DEFAULT '' COMMENT '规则配置',
  `stop_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '强制停止时间',

  `cron_exp` varchar(63) NOT NULL DEFAULT '' COMMENT 'cron时间表达式',
  `client_type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '客户端类型 1:H5 2:微信',

  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态 0:未开始 1:进行中 2:已结束 10:强行停止',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id_type_status` (`shop_id`, `type`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品营销活动配置表';

CREATE TABLE `shop_activity_map` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int(11) unsigned NOT NULL COMMENT '店铺ID',
  `activity_id` bigint(20) unsigned NOT NULL COMMENT '分类ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `product_sku_id` bigint(20) unsigned NOT NULL COMMENT '商品SKU ID，如整个商品都参加则填0',
  
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id_activity_id` (`product_id`, `activity_id`),
  KEY `idx_activity_id` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='营销活动-商品对应关系表';

