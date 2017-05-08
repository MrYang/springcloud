# springcloud-eureka client

eureka 注册服务

添加依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-eureka'
compile 'org.springframework.boot:spring-boot-starter-web'
```


配置

```yaml
server:
  port: 8762

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

spring:
  application:
    name: eureka-client
```

`spring.application.name` 表示向eureka注册的client 名字

启动

打开浏览器访问`http://localhost:8762/hello?name=foo`可查看返回

`SERVER_PORT=8763 gradle bootRun` 可开启第二个client, 端口号为8763
