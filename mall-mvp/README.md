# 商城系统架构

## 商品模块
[数据库DDL](sql/product_ddl.sql)

### 商品规格
一共3张表：
* product_sku
* product_spec_option：规格项名称，例如 颜色、存储容量
* product_spec_item：规格项取值，例如 金色/银色/海军蓝

以商品ID = 150为例：

product_spec_option表如下：
![image](https://user-images.githubusercontent.com/13992911/111977283-84127480-8b3d-11eb-9cb3-e315eae3991d.png)

product_spec_item表如下：
![image](https://user-images.githubusercontent.com/13992911/111977319-8ffe3680-8b3d-11eb-8720-350ae824a809.png)

product_sku表数据如下：
![image](https://user-images.githubusercontent.com/13992911/111977486-bf14a800-8b3d-11eb-9d2d-0f78b09fcc79.png)

### 商品浏览权限

### 商品库快照


## 营销模块
[数据库DDL](sql/activity_ddl.sql)

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


## 订单模块

