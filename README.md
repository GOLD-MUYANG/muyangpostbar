# Spring Boot 项目说明

这是一个基于 Spring Boot 框架的项目，主要用于演示和开发相关的功能。

## 项目信息

### 基本信息
- **项目名称**：`muyangpostbar`
- **项目描述**：`模仿百度贴吧，运用所学技术实现的简单网络论坛`
- **版本号**：`0.0.1-SNAPSHOT`
- **Java 版本**：`1.8`

### 父项目
- **GroupId**：`org.springframework.boot`
- **ArtifactId**：`spring-boot-starter-parent`
- **版本号**：`2.4.5`

## 依赖信息

### 核心依赖
- `spring-boot-starter`：Spring Boot 核心启动器，提供了自动配置和起步依赖。
- `spring-boot-starter-test`：用于单元测试，作用范围为 `test`。
- `spring-boot-starter-web`：Spring Boot 的 Web 启动器，用于构建 Web 应用。

### 开发工具依赖
- `spring-boot-devtools`：Spring Boot 开发工具，可提供热部署等开发时的便利功能，该依赖为可选依赖。
- `lombok`：用于简化 Java 代码，减少样板代码。

### JSON 处理依赖
- `spring-boot-starter-json`：Spring Boot 的 JSON 处理启动器，作用范围为 `compile`。
- `fastjson`：阿里巴巴的 JSON 处理库，版本为 `1.2.58`。

### 数据库相关依赖
- `mysql-connector-java`：MySQL 数据库连接驱动，作用范围为 `runtime`。
- `mybatis-spring-boot-starter`：MyBatis 与 Spring Boot 的集成启动器，版本为 `2.1.3`。
- `mybatis-plus-boot-starter`：MyBatis-Plus 与 Spring Boot 的集成启动器，版本为 `3.4.2`。

### API 文档相关依赖
- `springfox-swagger2`：用于生成 Swagger API 文档，版本为 `2.7.0`。
- `springfox-swagger-ui`：Swagger UI，用于展示 API 文档，版本为 `2.7.0`。

### 测试依赖
- `spring-boot-test`：Spring Boot 测试相关依赖。
- `junit`：JUnit 测试框架。
- `spring-test`：Spring 测试框架。

## 构建插件

### Spring Boot Maven 插件
- `spring-boot-maven-plugin`：用于打包和运行 Spring Boot 应用，版本为 `2.4.5`。配置了 `fork` 为 `true`，表示在构建过程中使用独立的 JVM 进程。

## 如何运行

1. 确保你已经安装了 Java 1.8 及以上版本和 Maven 构建工具。
2. 克隆本项目到本地：
   ```sh
   git clone <项目仓库地址>
   cd <项目目录>
