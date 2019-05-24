package com.anyway.client.controller;

import com.anyway.client.remote.feign.AnywayAdminRemote;
import com.anyway.client.remote.feign.AnywayApiRemote;
import com.anyway.common.utils.httpclient.HttpClientUtil;
import com.anyway.ipip.bean.LocationInfo;
import com.anyway.ipip.util.IpLocationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        log.info(">>>>>>>>>>>>>>>>>>");
        LocationInfo locationInfo = IpLocationHelper.getIpLocation("123.127.24.226");
        log.info("{}", locationInfo);
        String url = "http://localhost:8080/search/recommend";
        Map<String, Object> params = new HashMap<>();
        params.put("size", 20);
        String str = HttpClientUtil.doGet(url, params);
        log.info(">>>{}", str);
        String url2 = "http://localhost:8080/search/following";
        String json = "{'uid': 94390873}";
        String str2 = HttpClientUtil.doPostOfJson(url2, null, json);
        log.info(">>>{}", str2);
        return "success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sayHello")
    public String hello(String name) {
        return anywayApiRemote.sayHello(name);
    }

}

