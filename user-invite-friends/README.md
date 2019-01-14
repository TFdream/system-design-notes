## 业务场景
邀请好友注册、邀请好友做任务。

本文以邀请好友注册功能为例，邀请好友通常有以下几种形式：
1. 新客通过老客的邀请码进行注册；
2. 新客通过老客分享的邀请链接进行注册。

## 应用案例
极客邦App

LP页链接: https://time.geekbang.org/activity/getinvite?gk_ucode=7AEA1F8EC4A088&from=singlemessage

宜人财富App

LP页链接: https://yqz.yirendai.com/zhuan/join.html?rel=ADAAYlMzB2tTNQBuAGQ%3D&coef=10&sharefrom=null&from=singlemessage


## 设计思路
引导用户跳转到某个注册LP页面(链接上带上用户的ucode)，用户输入手机号后注册(如果有邀请码的话则输入邀请码)，后台记录对应的邀请关系

## API接口
### 1.获取老客分享链接
获取分享页面链接参数
#### 1.1 API Path
/api/user/invite/share

#### 1.2 请求方式
HTTP GET

#### 1.3 请求参数
无

#### 1.4 返回结果
```
{
    "code": 1, 
    "message": "OK", 
    "data": {
        "lpUrl": "https://xxx.com/user/invite?ucode=7AEA1F8EC4A088&utm_source=123&from=singlemessage"
    }
}
```

### 2.用户注册接口
需解析前端页面传递的ucode信息。
获取分享页面链接参数
#### 2.1 API Path
/api/user/registry

#### 2.2 请求方式
HTTP POST

#### 2.3 请求参数
| 参数名称 | 类型 | 是否必选 | 描述 |
| --- | --- | --- |--- |
| mobile | int | Y | 活动id |
| code | string | Y | 注册验证码 |
| ucode | string | N | 注册页面ucode |
| utm_source | string | N | 注册页面utm_source |

#### 2.4 返回结果
```
{
    "code": 1, 
    "message": "OK", 
    "data": {
        "success": 1
    }
}
```
