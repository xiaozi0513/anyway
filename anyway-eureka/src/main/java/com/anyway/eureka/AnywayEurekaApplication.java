package com.anyway.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka服务发现和注册中心 启动类
 *
 * @author: wang_hui
 * @date: 2018/10/19 下午6:18
 */
@EnableEurekaServer
@SpringBootApplication
public class AnywayEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnywayEurekaApplication.class, args);
    }

}
