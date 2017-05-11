package com.zz.eureka.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Yang
 * @since 2017-05-08
 */

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
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
