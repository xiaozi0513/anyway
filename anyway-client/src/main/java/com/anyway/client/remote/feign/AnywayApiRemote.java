package com.anyway.client.remote.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wang_hui
 * @date: 2019/4/15 下午5:07
 */
@FeignClient(name = "anyway-api")
public interface AnywayApiRemote {

    @RequestMapping(method = RequestMethod.GET, value = "/api/say/hello")
    String sayHello(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/api/test")
    String test();

}
