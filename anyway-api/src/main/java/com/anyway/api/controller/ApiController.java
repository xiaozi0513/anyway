package com.anyway.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wang_hui
 * @date: 2019/1/8 上午10:52
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping("/test")
    public String test() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return "success";
    }

    @RequestMapping("/say/hello")
    public String sayHello(String name) {
        log.info("receive info from client, name: " + name);
        return "Hello, " + name;
    }

}
