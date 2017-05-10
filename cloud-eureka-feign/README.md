# springcloud-feign


feign 负载均衡请求，封装了ribbon

添加依赖

```groovy
compile 'org.springframework.cloud:spring-cloud-starter-eureka'
compile 'org.springframework.cloud:spring-cloud-starter-feign'
compile 'org.springframework.boot:spring-boot-starter-web'
```

配置， 向eureka注册

```yaml
server:
  port: 8765

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

spring:
  application:
    name: eureka-feign
```

发送请求:`eureka-client` 为eureka 上注册的名字

```java
@FeignClient(value = "eureka-client", fallback = FeignErrorHelloService.class)
public interface FeignHelloService {

    @RequestMapping("/hello")
    String hello(@RequestParam("name") String name);
}
```

请求流程: web->feign->eureka-client(多个)，feign充当负载均衡器

添加熔断器：`FeignErrorHelloService`实现`FeignHelloService` 接口, 并添加`@Component` 注解
