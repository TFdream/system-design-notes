
CREATE DATABASE  `mall_mvp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `display_order` int(11) NOT NULL DEFAULT '0',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '商品类型 1:实物 2:虚拟商品 3:电子卡密',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `sub_title` varchar(255) NOT NULL DEFAULT '' COMMENT '副标题',
  `short_title` varchar(255) NOT NULL DEFAULT '' COMMENT '短标题',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '商品状态 0:放置仓库 1:上架售卖 2:上架隐藏 3:定时上架 -1:后台删除',
  `thumb` varchar(255) NOT NULL DEFAULT '' COMMENT '商品主图',
  `thumbs` varchar(1024) COMMENT '商品轮播图',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '售卖价格',
  `original_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '划线价格',
  `cost_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '成本价',
  `price_hide` tinyint(1) NOT NULL DEFAULT '0',
  `min_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `max_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `has_option` tinyint(1) NOT NULL DEFAULT '0' COMMENT '规格类型 1:多规格商品 0:无规格',
  `product_code` varchar(20) NOT NULL DEFAULT '' COMMENT '商品编码 格式:XXXX-YYYYYYYY-ZZ',
  `product_sn` varchar(50) NOT NULL DEFAULT '' COMMENT '商品条形码',
  `stock` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品库存',
  `stock_warning` int(10) NOT NULL DEFAULT '0' COMMENT '库存预警',
  `stock_hide` tinyint(1) NOT NULL DEFAULT '1' COMMENT '商品详情隐藏库存 1:隐藏 0:不隐藏',
  `stock_cnf` int(11) NOT NULL DEFAULT '0',
  `sales_count` int(11) NOT NULL DEFAULT '0' COMMENT '销量',
  `sales_hide` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商品详情隐藏销量 1:隐藏 0:不隐藏',
  `virtual_sales` int(10) NOT NULL DEFAULT '0' COMMENT '已出售数',
  
  `unit` varchar(10) NOT NULL DEFAULT '' COMMENT '单位',
  `weight` varchar(10) NOT NULL DEFAULT '' COMMENT '重量(千克)',
  `volume` varchar(10) NOT NULL DEFAULT '' COMMENT '体积(m³)',
  
  `auto_complete` tinyint(1) NOT NULL DEFAULT '0',

  `dispatch_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '快递运费类型 0:运费模板 1:统一运费',
  `dispatch_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '运费价格',
  `dispatch_id` int(11) DEFAULT '0' COMMENT '运费模板ID 关联es_shop_dispatch表id',
  `dispatch_mode` varchar(20) DEFAULT '1' COMMENT '物流支持 1:快递 2:同城配送 3:上门自提',
  `default_dispatch_mode` varchar(20) DEFAULT '1' COMMENT '默认物流支持',
  `dispatch_hide` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商品详情隐藏快递 1:隐藏 0:不隐藏',
  `is_buy_num_limit` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开启限购 1:限购 0:不限购',
  `max_buy_total` int(11) NOT NULL DEFAULT '0' COMMENT '每人限购数量上限',
  `max_buy_once` int(11) NOT NULL DEFAULT '0' COMMENT '单次下单购买限购数量',
  `min_buy` int(11) NOT NULL DEFAULT '0' COMMENT '起购数量',
  
  `content` text NOT NULL COMMENT '商品详情',
  `view_count` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',

  `is_refund_support` int(11) NOT NULL DEFAULT '1' COMMENT '是否支持售后维权 1:支持 0:不支持',
  `related_goods` varchar(255) NOT NULL DEFAULT '' COMMENT '关联商品',

  `audit_status` tinyint(2) NOT NULL DEFAULT '1',
  `refuse_reason` varchar(255) NOT NULL DEFAULT '',

  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '商品版本号',
  
  `putaway_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上架时间',
  `sellout_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '售罄时间',
  `delete_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品基础信息表';


CREATE TABLE `product_category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int(11) unsigned NOT NULL COMMENT '店铺ID',

  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '分类名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父分类id',
  `thumb` varchar(255) NOT NULL DEFAULT '' COMMENT '分类图片',

  `level` tinyint(2) NOT NULL DEFAULT '0' COMMENT '分类等级（一级、二级、三级分类）',
  `display_order` smallint(4) NOT NULL DEFAULT '0' COMMENT '展示顺序 越小越靠前',

  `state` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态 1:正常 0:无效',

  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`, `parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

CREATE TABLE `product_category_map` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int(11) unsigned NOT NULL COMMENT '店铺ID',
  `category_id` int(11) unsigned NOT NULL COMMENT '分类ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  
  `state` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态 1:正常 0:无效',

  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_shop_category_id` (`shop_id`, `category_id`, `product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类对应关系表';


CREATE TABLE `product_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '分组名称',
  `remark` varchar(512)  NOT NULL DEFAULT '' COMMENT '备注',
  
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1:正常 0:无效',

  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_shopid` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分组表';

CREATE TABLE `product_group_map` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int(11) NOT NULL COMMENT '店铺ID',
  
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `group_id` int(11) NOT NULL COMMENT '分组ID',
  
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1:正常 0:无效',

  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_shop_group_id` (`shop_id`, `group_id`, `product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分组对应关系表';

CREATE TABLE `product_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `name` varchar(60) NOT NULL DEFAULT '' COMMENT '名称',
  `remark` varchar(512)  NOT NULL DEFAULT '' COMMENT '备注',
  
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1:正常 0:无效',
  
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品标签表';

CREATE TABLE `product_label_map` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',

  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `label_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品标签ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',

  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1:正常 0:无效',

  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_goods_id` (`shop_id`, `product_id`),
  KEY `idx_laber_id` (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品标签对应关系表'


CREATE TABLE `product_sku` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `shop_id` int(11) unsigned NOT NULL COMMENT '店铺ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '规格名称',
  `thumb` varchar(255) NOT NULL DEFAULT '' COMMENT '规格图片链接',
  `price` decimal(12,2) NOT NULL COMMENT '售卖价格',
  `cost_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '成本价',
  `original_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '划线价格',
  `product_code` varchar(20) NOT NULL DEFAULT '' COMMENT '商品编码 格式:XXXX-YYYYYYYY-ZZ',
  `product_sn` varchar(50) NOT NULL DEFAULT '' COMMENT '商品条形码',
  
  `stock` int(11)  NOT NULL DEFAULT '0' COMMENT '库存',
  `stock_warning` int(11) NOT NULL DEFAULT '0' COMMENT '库存预警',
  `sales_count` int(11) NOT NULL DEFAULT '0' COMMENT '销量', 

  `weight` varchar(10) NOT NULL DEFAULT '' COMMENT '重量(千克)',
  `volume` varchar(10) NOT NULL DEFAULT '' COMMENT '体积(m³)',

  `spec_item_ids` varchar(128) COMMENT '规格项id 多个值逗号分割 关联product_spec_item表主键ID',

  `display_order` smallint(4) NOT NULL DEFAULT '0' COMMENT '展示顺序 越小越靠前',
  
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_shop_id_product_code` (`shop_id`, `product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格信息表';

CREATE TABLE `product_spec_option` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `shop_id` int(11) unsigned NOT NULL COMMENT '店铺ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '商品规格项名称',
  
  `display_order` smallint(4) NOT NULL DEFAULT '0' COMMENT '展示顺序 越小越靠前',
  
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品规格项-名称表';

CREATE TABLE `product_spec_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  
  `spec_id` int(11) unsigned NOT NULL  COMMENT '商品规格项id 关联product_spec_option表主键ID',

  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '商品规格项取值',

  `display_order` smallint(4) NOT NULL DEFAULT '0' COMMENT '展示顺序 越小越靠前',

  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_spec_id` (`spec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品规格项-取值表';

CREATE TABLE `product_perm_map` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `perm_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '权限类型 0:浏览 1:购买',
  `member_type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户类型 1:会员等级 2:会员标签',
  `type_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户身份ID',

  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_product_id` (`product_id`, `perm_type`, `type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品权限表';
