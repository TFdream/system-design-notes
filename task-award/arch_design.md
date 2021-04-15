# 技术架构设计

## 数据库表结构设计

任务表：
```
CREATE TABLE `bonus_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `task_name` varchar(128) NOT NULL DEFAULT '' COMMENT '任务名称',
  `type` tinyint(2) NOT NULL COMMENT '任务类型，1:新手任务 2:限时任务',
  `cycle_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '任务周期类型 0:不重复 1:每日一次 2:每周一次 3:每月一次 4:自定义周期',
  `cycle_time` int(10) NOT NULL DEFAULT '0' COMMENT '任务周期自定义时间 单位：小时',
  `redirect_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '页面跳转方式 0:无需跳转 1:直接跳转 2:请求XX接口获取跳转地址 3:App统跳',
  `redirect_url` varchar(255) NOT NULL DEFAULT '' COMMENT '直接跳转地址，或者请求跳转地址的url',
  `completion_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '任务完成方式 0: 自动完成 1:需要手动完成且需要回调 2:手动完成且无需回调',
  `completion_callback` varchar(128) NOT NULL DEFAULT '' COMMENT '任务回调配置',
  `bonus_num` int(10) NOT NULL DEFAULT '0' COMMENT '任务完成奖励数量',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '任务描述',
  `show_order` int(10) NOT NULL DEFAULT '1' COMMENT '页面展示顺序 ，值越小的排在前面',
  `is_gray_test` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否属于灰度测试 1:灰度内测任务 0:非灰度(全量发布)',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是被删除 0:否 1:是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务配置';
```

用户任务表：
```

```
