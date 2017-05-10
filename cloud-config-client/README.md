# springcloud-config client

添加依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-config'
compile 'org.springframework.cloud:spring-cloud-starter-bus-kafka'
compile 'org.springframework.boot:spring-boot-starter-web'
compile 'org.springframework.boot:spring-boot-starter-actuator'
```

配置

到`{spring.cloud.config.url}/{spring.application.name}/{spring.cloud.config.profile}` 
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

配置中心的地址需要放在bootstrap.yml，这个配置文件是由"根"上下文优先加载，
可以保证程序启动之初就感知到远程配置中心的存在，并从远程获取配置，随后继续启动系统

添加`spring-cloud-starter-bus-kafka`, `spring-boot-starter-actuator` 依赖后，
server 端执行`curl -X POST http://localhost:8888/bus/refresh`，所有client能自动重新加载配置


`spring-boot-actuator` 提供一些接口修改系统配置

`curl -X POST http://localhost:8881/env -d name=Mr.Yang3`, `curl -X POST http://localhost:8881/refresh` 即可修改系统中name 变量

`http://localhost:8881/bus/env`, `http://localhost:8881/bus/refresh` 对系统中所有的client 生效