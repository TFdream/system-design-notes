# 商城系统架构

## 商品模块

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


## 营销模块

## 订单模块

