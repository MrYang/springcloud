package com.zz.eureka.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mr.Yang
 * @since 2017-05-08
 */

@FeignClient("eureka-client")
public interface FeignHelloService {

    @RequestMapping("/hello")
    String hello(@RequestParam("name") String name);
}
