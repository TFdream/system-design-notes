## 商品模块

### 功能截图
功能截图如下：
![image](https://user-images.githubusercontent.com/13992911/111982411-e66e7380-8b43-11eb-9545-652b83daa082.png)

![image](https://user-images.githubusercontent.com/13992911/111982453-f38b6280-8b43-11eb-8939-058353885e42.png)

![image](https://user-images.githubusercontent.com/13992911/111982190-a0191480-8b43-11eb-9244-e30f71c8d1f1.png)

![image](https://user-images.githubusercontent.com/13992911/111982245-b1622100-8b43-11eb-9bde-483d5a0cea31.png)

![image](https://user-images.githubusercontent.com/13992911/111982309-c63eb480-8b43-11eb-9193-26d17929b23c.png)

### 数据库DDL
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

