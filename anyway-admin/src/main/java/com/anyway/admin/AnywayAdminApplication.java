package com.anyway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {"com.anyway.admin", "com.anyway.common"})
public class AnywayAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnywayAdminApplication.class, args);
    }
}
