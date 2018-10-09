package com.anyway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server启动类
 */
@EnableEurekaServer
@SpringBootApplication
public class AnywayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnywayServerApplication.class, args);
    }
}
