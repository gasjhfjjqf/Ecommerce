###  E-Commerce Backend System

一个基于 Spring Boot + MyBatis + Redis + ShardingSphere-JDBC 的简易电商系统后端，支持商品信息的基本增删改查，并通过 ShardingSphere-JDBC 实现 MySQL 分库分表功能，提升系统的水平扩展能力。

------

### 项目特性

- 商品的增删改查功能（CRUD）
- Redis 缓存优化（基于 Spring Cache）
-  使用 MyBatis 做持久层映射
- 支持基于注解的缓存注入

------

###  技术栈

| 层级     | 技术                  |
| -------- | --------------------- |
| 框架     | Spring Boot 3.x       |
| ORM      | MyBatis + Mapper 接口 |
| 缓存     | Redis + Spring Cache  |
| 数据库   | MySQL 8.x             |
| 构建工具 | Maven                 |
| 接口测试 | Postman               |



------

### 项目结构

```
bash复制编辑ecommerce/
├── entity/          # 实体类（如 Product）
├── mapper/          # MyBatis 映射接口
├── service/         # 业务逻辑层
├── controller/      # REST 接口层
├── resources/
│   ├── application.properties  # 配置文件
│   └── mapper/      # MyBatis XML（如果有）
```

------

### 数据库说明

- 初始化 SQL 示例：

```
sql
CREATE TABLE product_0 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    description TEXT,
    price DOUBLE,
    stock INT
);
```

------

### 启动方式

1. **克隆项目并导入 IDE（如 IntelliJ IDEA）**
2. **配置 `application.properties` 数据源与分片规则**
3. **运行主类 `EcommerceApplication.java`**
4. **调用测试接口验证分库分表是否生效**

------

###  示例接口（部分）

| 功能         | 请求方式 | URL                    |
| ------------ | -------- | ---------------------- |
| 插入测试商品 | POST     | `/product/test-insert` |
| 查询所有商品 | GET      | `/product/all`         |
| 查询单个商品 | GET      | `/product/{id}`        |