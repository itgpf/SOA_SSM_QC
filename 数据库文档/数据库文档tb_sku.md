# 数据库文档 

### tb_sku  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | 商品id | VARCHAR   |      |      |
| sn  | 商品条码 | VARCHAR   |      |      |
| name  | SKU名称 | VARCHAR   |      |      |
| price  | 价格（分） | INT   |      |      |
| num  | 库存数量 | INT   |      |      |
| alert_num  | 库存预警数量 | INT   |      |      |
| image  | 商品图片 | VARCHAR   |      |      |
| images  | 商品图片列表 | VARCHAR   |      |      |
| weight  | 重量（克） | INT   |      |      |
| create_time  | 创建时间 | DATETIME   |      |      |
| update_time  | 更新时间 | DATETIME   |      |      |
| spu_id  | SPUID | VARCHAR   |      |      |
| category_id  | 类目ID | INT   |      |      |
| category_name  | 类目名称 | VARCHAR   |      |      |
| brand_name  | 品牌名称 | VARCHAR   |      |      |
| spec  | 规格 | VARCHAR   |      |      |
| sale_num  | 销量 | INT   |      |      |
| comment_num  | 评论数 | INT   |      |      |
| status  | 商品状态 1-正常，2-下架，3-删除 | CHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /sku/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": 商品id,
	"sn": 商品条码,
	"name": SKU名称,
	"price": 价格（分）,
	"num": 库存数量,
	"alertNum": 库存预警数量,
	"image": 商品图片,
	"images": 商品图片列表,
	"weight": 重量（克）,
	"createTime": 创建时间,
	"updateTime": 更新时间,
	"spuId": SPUID,
	"categoryId": 类目ID,
	"categoryName": 类目名称,
	"brandName": 品牌名称,
	"spec": 规格,
	"saleNum": 销量,
	"commentNum": 评论数,
	"status": 商品状态 1-正常，2-下架，3-删除,

},
.......
]
```



#### 分页查询数据  

##### url

> /sku/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /sku/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": 商品id,
	"sn": 商品条码,
	"name": SKU名称,
	"price": 价格（分）,
	"num": 库存数量,
	"alertNum": 库存预警数量,
	"image": 商品图片,
	"images": 商品图片列表,
	"weight": 重量（克）,
	"createTime": 创建时间,
	"updateTime": 更新时间,
	"spuId": SPUID,
	"categoryId": 类目ID,
	"categoryName": 类目名称,
	"brandName": 品牌名称,
	"spec": 规格,
	"saleNum": 销量,
	"commentNum": 评论数,
	"status": 商品状态 1-正常，2-下架，3-删除,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /sku/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /sku/findList.do
{
	"id": 商品id,
	"sn": 商品条码,
	"name": SKU名称,
	"price": 价格（分）,
	"num": 库存数量,
	"alertNum": 库存预警数量,
	"image": 商品图片,
	"images": 商品图片列表,
	"weight": 重量（克）,
	"createTime": 创建时间,
	"updateTime": 更新时间,
	"spuId": SPUID,
	"categoryId": 类目ID,
	"categoryName": 类目名称,
	"brandName": 品牌名称,
	"spec": 规格,
	"saleNum": 销量,
	"commentNum": 评论数,
	"status": 商品状态 1-正常，2-下架，3-删除,

}
```

##### 返回格式

```
[{
	"id": 商品id,
	"sn": 商品条码,
	"name": SKU名称,
	"price": 价格（分）,
	"num": 库存数量,
	"alertNum": 库存预警数量,
	"image": 商品图片,
	"images": 商品图片列表,
	"weight": 重量（克）,
	"createTime": 创建时间,
	"updateTime": 更新时间,
	"spuId": SPUID,
	"categoryId": 类目ID,
	"categoryName": 类目名称,
	"brandName": 品牌名称,
	"spec": 规格,
	"saleNum": 销量,
	"commentNum": 评论数,
	"status": 商品状态 1-正常，2-下架，3-删除,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /sku/findPage.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |
| page      | true | int  | 页码（GET）      |
| size      | true | int  | 每页记录数（GET）   |

例子：

```
POST /sku/findPage.do?page=1&size=10
{
	"id": 商品id,
	"sn": 商品条码,
	"name": SKU名称,
	"price": 价格（分）,
	"num": 库存数量,
	"alertNum": 库存预警数量,
	"image": 商品图片,
	"images": 商品图片列表,
	"weight": 重量（克）,
	"createTime": 创建时间,
	"updateTime": 更新时间,
	"spuId": SPUID,
	"categoryId": 类目ID,
	"categoryName": 类目名称,
	"brandName": 品牌名称,
	"spec": 规格,
	"saleNum": 销量,
	"commentNum": 评论数,
	"status": 商品状态 1-正常，2-下架，3-删除,

}
```

##### 返回格式

```
{rows:[{
	"id": 商品id,
	"sn": 商品条码,
	"name": SKU名称,
	"price": 价格（分）,
	"num": 库存数量,
	"alertNum": 库存预警数量,
	"image": 商品图片,
	"images": 商品图片列表,
	"weight": 重量（克）,
	"createTime": 创建时间,
	"updateTime": 更新时间,
	"spuId": SPUID,
	"categoryId": 类目ID,
	"categoryName": 类目名称,
	"brandName": 品牌名称,
	"spec": 规格,
	"saleNum": 销量,
	"commentNum": 评论数,
	"status": 商品状态 1-正常，2-下架，3-删除,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /sku/findById.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键  |

例子：

```

```



#### 增加数据  

##### url

> /sku/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| sku | true | sku | 实体对象 |

##### 返回格式

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败



#### 修改数据  

##### url

> /sku/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| sku | true | sku | 实体对象 |

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败



#### 删除数据  

##### url

> /sku/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /sku/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
