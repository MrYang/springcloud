# springcloud-eureka server


eureka 服务注册组件

添加依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-eureka'
compile 'org.springframework.cloud:spring-cloud-starter-eureka-server'
```

配置

```yaml
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

设置`eureka.client.registerWithEureka`,`eureka.client.fetchRegistry` 为false，表示该client 为一个eureka server

启动打开浏览器访问`http://localhost:8761`可查看管理界面