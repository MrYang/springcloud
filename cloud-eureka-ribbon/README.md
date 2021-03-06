# springcloud-ribbon


ribbon 负载均衡请求

添加依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-eureka'
compile 'org.springframework.cloud:spring-cloud-starter-ribbon'
compile 'org.springframework.cloud:spring-cloud-starter-hystrix'
compile 'org.springframework.boot:spring-boot-starter-web'
```

配置， 向eureka注册

```yaml
server:
  port: 8764

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

spring:
  application:
    name: eureka-ribbon
```

设置负载均衡`restTemplate`

```java
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

发送请求:`eureka-client` 为eureka 上注册的名字，既之前的eureka-client

`restTemplate.getForObject("http://eureka-client/hello?name=" + name, String.class);`

请求流程: web->ribbon->eureka-client(多个)，ribbon充当了一个负载均衡器

添加熔断器：当`http://eureka-client/hello` 请求不可访问时，调用`hystrixMethod`方法返回

```java
    @HystrixCommand(fallbackMethod = "hystrixMethod")
    public String hello(String name) {
        return restTemplate.getForObject("http://eureka-client/hello?name=" + name, String.class);
    }

    public String hystrixMethod(String name) {
        return "sorry:" + name + ", error!!";
    }
```

添加@EnableHystrixDashboard 注解，浏览器打开 `http://localhost:8764/hystrix`

输入`http://localhost:8764/hystrix.stream` 点击`monitor stream` 查看app的服务调用情况


添加spring-cloud-sleuth,zipkin 链路调用追踪分析

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-sleuth'
compile 'org.springframework.cloud:spring-cloud-sleuth-zipkin'
```

配置：

```yaml
spring:
  zipkin:
    base-url: http://localhost:8767
```

Spring检查有sleuth和zipkin，自动在RestTemplate的调用过程中向HTTP请求注入追踪信息，并向zipkin Server发送
