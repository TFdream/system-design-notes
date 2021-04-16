# 技术方案设计

## 数据库设计

活动表：
```
CREATE TABLE `operation_activity` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) NOT NULL COMMENT '活动名称',
  `type` tinyint(2) NOT NULL COMMENT '活动类型',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `offline_time` datetime NOT NULL COMMENT '下线时间',
  `state` tinyint(2) NOT NULL COMMENT '状态',
  `config` varchar(2000) DEFAULT NULL COMMENT '活动配置信息',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '主活动ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='运营活动';
```

其中，阶梯奖励组件 config 如下：
```
{
    "products":[
        {
            "type":5,
            "periods":[3, 6, 12]
        },
        {
            "type":10,
            "periods":[3]
        }
    ],
    "ladders":[
        {
            "index":1,
            "investThreshold":"2000",
            "awardId":241,
            "awardName":"回馈红包20",
            "awardType":1
        },
        {
            "index":1,
            "investThreshold":"10000",
            "awardId":240,
            "awardName":"人数50元",
            "awardType":1
        }
    ]
}
```

用户达标阶梯奖励记录表：
```
CREATE TABLE `operation_ladder-reward` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_id` int(10) NOT NULL COMMENT '活动id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `ladder_threshold` int(10) NOT NULL COMMENT '阶梯投资金额阈值',
  `award_id` int(10) NOT NULL COMMENT '奖品id',
  `award_name` varchar(40) NOT NULL COMMENT '奖品名称',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态 1:待领取, 2:已领取',
  `user_award_id` bigint(10) DEFAULT NULL COMMENT '关联operation_user_award表id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`,`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阶梯奖励记录表';
```

