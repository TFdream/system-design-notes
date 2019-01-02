# 用户注册/登录系统设计

## 表结构设计
user表：
```
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
  `utm_source` varchar(40) NOT NULL DEFAULT ''  COMMENT '用户注册渠道',
  `invitation_user_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '邀请好友用户id',
  `status` tinyint(2) unsigned NOT NULL DEFAULT 1 COMMENT '账号状态, 1:正常, 2:冻结',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT 'version',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';
```

## API接口列表
### 1.发送短信验证码
发送注册/登录 短信验证码

#### 1.1 API Path
/api/user/auth-code
#### 1.2 请求方式
HTTP POST

#### 1.3 请求参数


#### 1.4 返回结果


### 2.用户注册接口
通过手机号注册账号

#### 2.1 API Path
/api/user/registry
#### 2.2 请求方式
HTTP POST

#### 2.3 请求参数


#### 2.4 返回结果


### 3.用户登录接口
#### 3.1 API Path
/api/user/login
#### 3.2 请求方式
HTTP POST

#### 3.3 请求参数


#### 3.4 返回结果


### 4.用户token续期接口
客户端App（Android/iOS）在应用启动时调用，已登录用户调用一次会将token有效期延长1天。

#### 4.1 API Path
/api/user/token-renewal
#### 4.2 请求方式
HTTP POST

#### 4.3 请求参数


#### 4.4 返回结果


### 5.用户登出接口
用户主动退出登录，此时需清除用户token数据。

#### 5.1 API Path
/api/user/logout
#### 5.2 请求方式
HTTP POST

#### 5.3 请求参数


#### 5.4 返回结果
