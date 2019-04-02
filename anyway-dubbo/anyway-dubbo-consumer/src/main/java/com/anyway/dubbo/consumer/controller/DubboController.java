package com.anyway.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.anyway.dubbo.interfaces.api.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wang_hui
 * @date: 2019/1/9 上午10:49
 */
@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Reference(version = "1.0.0", url = "dubbo://localhost:12345", timeout = 10)
    public DemoService demoService;

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return demoService.sayHello(name);
    }

    public String test() {
        return "this is a test.";
    }



}
