package com.anyway.apollo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wang_hui
 * @date: 2019/4/22 下午4:18
 */
@Slf4j
@RestController
@RequestMapping("/apollo/demo")
public class DemoController {

    @Value("${hello.name:hello}")
    private String hello;

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello(String name) {
        return hello + ", " + name;
    }

}
