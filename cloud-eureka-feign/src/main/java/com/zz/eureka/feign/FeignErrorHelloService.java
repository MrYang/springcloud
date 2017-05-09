package com.zz.eureka.feign;

import org.springframework.stereotype.Component;

/**
 * @author Mr.Yang
 * @since 2017-05-08
 */

@Component
public class FeignErrorHelloService implements FeignHelloService {

    @Override
    public String hello(String name) {
        return "hello:" + name + ", error!!";
    }
}
