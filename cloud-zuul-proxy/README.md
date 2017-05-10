# springcloud-zuul


zuul 提供动态路由，监控，弹性，安全, 网关服务

添加依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-eureka'
compile 'org.springframework.cloud:spring-cloud-starter-zuul'
compile 'org.springframework.boot:spring-boot-starter-web'
```

配置， 向eureka注册，/api-a/** 的请求实际由eureka-feign 完成。如请求`api-a/hello` 会指向`http://eureka-feign/hello`。

```yaml
server:
  port: 8766

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

spring:
  application:
    name: zuul-proxy

zuul:
  routes:
    api-a:
      path: /api-a/**
      serviceId: eureka-feign
    api-b:
      path: /api-b/**
      serviceId: eureka-ribbon
```

定制ZuulFilter，可以做安全控制，监控，日志等操作。