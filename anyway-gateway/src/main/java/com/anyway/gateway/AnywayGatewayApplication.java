package com.anyway.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务网关 启动类
 *
 * @author: wang_hui
 * @date: 2018/10/19 下午6:25
 */
@EnableEurekaClient
@SpringBootApplication
public class AnywayGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnywayGatewayApplication.class, args);
    }

}
