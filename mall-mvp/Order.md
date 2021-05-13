## 订单管理


### 表结构DDL

订单商品物流：
```
CREATE TABLE `es_shop_order_package` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int(11) NOT NULL COMMENT '店铺ID',
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `order_id` int(11) unsigned NOT NULL COMMENT '订单ID',
  `order_goods_id` bigint(20) unsigned  NOT NULL COMMENT '订单商品ID',
  `is_split` tinyint(1) NOT NULL DEFAULT '0' COMMENT '物流是否拆分为多个快递 1:是 0:否',
  `send_order` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '物流发货顺序',
  `express_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '快递公司ID，关联es_core_express表id',
  `express_name` varchar(50) NOT NULL DEFAULT '' COMMENT '快递公司名称',
  `express_sn` varchar(50) NOT NULL DEFAULT '' COMMENT '快递单号',

  `remark` varchar(128) NOT NULL COMMENT '订单发货备注',
  `upload_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上传物流时间',
  `finish_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '完成时间',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否已删除 0:未删除 1:已删除',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_order_goods_id` (`order_goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单物流信息表';
```

