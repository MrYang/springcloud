# springcloud-config server

添加依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-config-server'
compile 'org.springframework.cloud:spring-cloud-starter-config'
```

配置

```yaml
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