# springcloud-zipkin

zipkin 提供服务追踪分析

添加依赖

```groovy
compile 'org.springframework.boot:spring-boot-starter'
compile 'io.zipkin.java:zipkin-server'
runtime 'io.zipkin.java:zipkin-autoconfigure-ui'
```

配置

```yaml
server:
  port: 8767
  error:
    whitelabel:
      enabled: true

spring:
  application:
    name: zipkin-server
```

访问`http://localhost:8767` 查看服务调用情况, [客服端](https://github.com/MrYang/springcloud/tree/master/cloud-eureka-ribbon)发送调用链信息到该server