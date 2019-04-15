package com.anyway.client.controller;

import com.anyway.client.remote.feign.AnywayAdminRemote;
import com.anyway.client.remote.feign.AnywayApiRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wang_hui
 * @date: 2018/12/29 下午3:32
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private AnywayAdminRemote anywayAdminRemote;
    @Autowired
    private AnywayApiRemote anywayApiRemote;

    @RequestMapping("/user/all")
    public String getUserList() {
        return anywayAdminRemote.listUser();
    }

    @RequestMapping("/user/info")
    public String getUserInfo(@RequestParam("id") Long id) {
        return anywayAdminRemote.getUserById(id);
    }

    @RequestMapping("test")
    public String test() {
        log.info(">>>>>>>>>>>>>>>>>>>>");
        return "success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sayHello")
    public String hello(String name) {
        return anywayApiRemote.sayHello(name);
    }

}

