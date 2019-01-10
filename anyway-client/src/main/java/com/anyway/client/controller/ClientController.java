package com.anyway.client.controller;

import com.anyway.client.remote.AnywayApiRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private AnywayApiRemote anywayApiRemote;

    @RequestMapping("/user/all")
    public String getUserList() {
        return anywayApiRemote.listUser();
    }

    public static void main(String[] args) {
        long a = 690610976807055440L >> 11;
        int b = (int) (a & 4095L);
        System.out.println(String.format("%s%04d", new Object[]{"comment_core_", Integer.valueOf(b & 1023)}));

    }

    @RequestMapping("/user/info")
    public String getUserInfo(@RequestParam("id") Long id) {
        return anywayApiRemote.getUserById(id);
    }

    @RequestMapping("test")
    public String test() {
        log.info(">>>>>>>>>>>>>>>>>>>>");
        return "success";
    }

}
