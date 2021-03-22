## 营销模块

### 功能截图
功能截图如下：
![image](https://user-images.githubusercontent.com/13992911/111982501-04d46f00-8b44-11eb-96a6-aee6b40ac0d7.png)

![image](https://user-images.githubusercontent.com/13992911/111982588-1c135c80-8b44-11eb-8d6c-0b45e67c4605.png)

![image](https://user-images.githubusercontent.com/13992911/111981993-63e5b400-8b43-11eb-927b-0cd0a7662a31.png)

## 数据库DDL
[数据库DDL](/mall-mvp/sql/activity_ddl.sql)

### 表数据
shop_activity表如下：
![image](https://user-images.githubusercontent.com/13992911/111981480-b5417380-8b42-11eb-8244-592fc320498d.png)

shop_activity_map表如下：
![image](https://user-images.githubusercontent.com/13992911/111981604-da35e680-8b42-11eb-9cf1-58d9e2a2fa5a.png)

其中，rules为规格配置：
```
[
    {
        "type":"1",
        "value":"0",
        "max":"2500"
    },
    {
        "type":"2",
        "value":"1",
        "max":"2500"
    },
    {
        "type":"2",
        "value":"2",
        "max":"3000"
    },
    {
        "type":"2",
        "value":"3",
        "max":"3191"
    },
    {
        "type":"3",
        "value":"10",
        "max":"2999"
    },
    {
        "type":"3",
        "value":"12",
        "max":"2599"
    }
]
```

其中，type取值 1:全部会员 2:会员等级 3:会员标签

