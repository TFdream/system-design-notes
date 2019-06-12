
CREATE TABLE `question` (
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`question_no` varchar(40) NOT NULL COMMENT '题目编号',
`subject` varchar(256) NOT NULL COMMENT '题目内容',
`question_type` tinyint(2) NOT NULL DEFAULT 1 COMMENT '试题类型 1:单选, 2:多选, 3:判断',
`right_option_id` varchar(16) NOT NULL COMMENT '正确选项id, 多个答案逗号分隔',
`status` tinyint(2) NOT NULL DEFAULT 1 COMMENT '状态 1:未审核, 2:已审核通过, 3:已删除',
`create_time` datetime NOT NULL COMMENT '创建时间',
`update_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uniq_question_no` (`question_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试题题库';

CREATE TABLE `question_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `question_id` bigint(20) NOT NULL COMMENT '题目ID',
  `option_no` varchar(8) NOT NULL DEFAULT '' COMMENT '选项编号, 例如A、B、C或者1 2 3',
  `option_desc` varchar(256) NOT NULL COMMENT '选项描述',
  `option_rank` tinyint(2) NOT NULL DEFAULT 0 COMMENT '选项顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_question_id` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试题选项';

CREATE TABLE `user_answer_record` (
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`question_id` bigint(20) NOT NULL COMMENT '试题ID',
`user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
`submit_options` varchar(16) NOT NULL COMMENT '用户提交的选项',
`result_type` tinyint(1) NOT NULL COMMENT '用户答题结果1:正确, 2:错误, 3:超时',
`create_time` datetime NOT NULL COMMENT '创建时间',
`update_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`),
KEY `idx_user_question_id` (`user_id`,`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户答题记录';
