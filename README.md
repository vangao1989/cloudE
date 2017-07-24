## 项目介绍

　　基于spring cloud的分布式系统架构。提供整套的微服务组件，包括服务发现、服务治理、链路追踪、服务监控等。所有服务均支持在docker中运行。


### 组织结构

``` lua
cloudE
├── cloudE-eureka-server -- 服务发现
├── cloudE-config-server -- 分布式配置中心
├── cloudE-config-repo -- 配置文件
├── cloudE-admin-server -- 服务监控
├── cloudE-mapper -- mybatis生成模块
├── cloudE-zipkin-server -- 链路追踪

