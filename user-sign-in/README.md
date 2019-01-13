# 用户签到系统设计

## 表结构设计

## API接口
### 1.用户当天签到状态查询
查询用户当天签到状态
#### 1.1 API Path
/api/user/sign-in/status
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
        "todaySignIn": 1, 
        "continuousSignInDays": 1
    }
}
```

### 2.用户签到接口
用户签到
#### 2.1 API Path
/api/user/sign-in/tick
#### 2.2 请求方式
HTTP GET

#### 2.3 请求参数
无

#### 1.4 返回结果
```
{
    "code": 1, 
    "message": "OK", 
    "data": {
        "continuousSignInDays": 1
    }
}
```

### 3.查询用户当月签到记录
查询用户当月签到记录
#### 3.1 API Path
/api/user/sign-in/history
#### 3.2 请求方式
HTTP GET

#### 3.3 请求参数
无

#### 3.4 返回结果
```
{
    "code": 1, 
    "message": "OK", 
    "data": {
        "startDay": 20190101, 
        "endDay": 20190131, 
        "signInList": [
            20190101, 
            20190102, 
            20190103, 
            20190104, 
            20190106, 
            20190107
        ]
    }
}
```
