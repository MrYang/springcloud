package com.zz.eureka.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Yang
 * @since 2017-05-08
 */

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class EurekaFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignApplication.class, args);
    }

    @Autowired
    private FeignHelloService feignHelloService;

    @RequestMapping("hello")
    public String hello(String name) {
        return feignHelloService.hello(name);
    }


}
