package com.anyway.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心服务端
 *
 * @author: wang_hui
 * @date: 2018/10/25 下午3:02
 */
@SpringBootApplication
@EnableConfigServer
public class AnywayConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnywayConfigApplication.class, args);
    }

}
