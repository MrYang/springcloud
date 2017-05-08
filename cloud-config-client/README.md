# springcloud-config client

添加依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-config'
compile 'org.springframework.boot:spring-boot-starter-web'
```

配置

去`{spring.cloud.config.url}/{spring.application.name}/{spring.cloud.config.profile}` 
即 `http://localhost:8888/config-client/dev`取配置
```yaml
spring:
  application:
    name: config-client
  cloud:
    config:
      lable: master
      profile: ${config.profile:dev}
      url: http://localhost:8888/
      
server:
  port: 8881
```

配置中心的地址需要放在bootstrap.yml，这个配置文件是由“根”上下文优先加载，
可以保证程序启动之初就感知到远程配置中心的存在，并从远程获取配置，随后继续启动系统