# springcloud-config server

添加依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-config-server'
compile 'org.springframework.cloud:spring-cloud-starter-config'
```

配置

```yaml
server:
  port: 8888
  
spring:
  application:
    name: config-server
  cloud:
    config:
      lable: master   # git 分支
      server:
        git:
          uri: https://github.com/MrYang/springcloud.git
          searchPaths: cloud-config-repo  # git 项目下的文件夹
        #  basedir: git会克隆到本地文件中，一般为系统临时目录, 可以配置该参数为其他目录
        #native:  本地查找
        #  searchLocations: /data/config-repo
```

启动

- application：client应用名
- profile：环境
- label：版本(分支)

```properties
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

例如访问`http://localhost:8888/config-client/dev/master` 对应的内容是
`spring.cloud.config.server.git.searchPaths`配置的config-client-dev.yml 文件


配置改动push到各个config-client

添加bus-kafka依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-bus-kafka'
compile 'org.springframework.boot:spring-boot-starter-actuator'
```

添加配置, 配置kafka,zookeeper地址

```yaml
spring:
  application:
    name: config-server
  cloud:
    stream:
      kafka:
        binder:
          brokers: 127.0.0.1:9092
          zk-nodes: 127.0.0.1:2181
```

git searchPaths 中配置改动以后，执行 `curl -X POST http://localhost:8888/bus/refresh` 即推送最新的配置到client，
也可以改该地址配置为git web hook，push时能自动推送配置

注意client 需要配置`@RefreshScope`注解
