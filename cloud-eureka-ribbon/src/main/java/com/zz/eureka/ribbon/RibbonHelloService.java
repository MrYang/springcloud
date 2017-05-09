package com.zz.eureka.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mr.Yang
 * @since 2017-05-08
 */

@Service
public class RibbonHelloService {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("hello")
    @HystrixCommand(fallbackMethod = "hystrixMethod")
    public String hello(String name) {
        return restTemplate.getForObject("http://eureka-client/hello?name=" + name, String.class);
    }

    public String hystrixMethod(String name) {
        return "sorry:" + name + ", error!!";
    }
}
