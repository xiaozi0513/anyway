package com.anyway.client.remote.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: wang_hui
 * @date: 2018/12/29 下午3:15
 */
@FeignClient("anyway-admin")
public interface AnywayAdminRemote {

    @RequestMapping(method = RequestMethod.GET, value = "/user/list")
    String listUser();

    @RequestMapping(method = RequestMethod.GET, value = "/user/info/{id}")
    String getUserById(@PathVariable("id") Long userId);

}
