package com.anyway.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: wang_hui
 * @date: 2018/12/29 下午2:53
 */
@EnableFeignClients
@SpringBootApplication
public class AnywayClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnywayClientApplication.class, args);
    }
    
}
