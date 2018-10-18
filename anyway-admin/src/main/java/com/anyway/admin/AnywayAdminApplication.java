package com.anyway.admin;

import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {"com.anyway.admin", "com.anyway.common"})
public class AnywayAdminApplication {

    public static void main(String[] args) {
        SpringApplication bootstrap = new SpringApplication(AnywayAdminApplication.class);
        bootstrap.setBanner(new ResourceBanner(new ClassPathResource("banner.txt")));
        bootstrap.run(args);
//        SpringApplication.run(AnywayAdminApplication.class, args);
    }
}
