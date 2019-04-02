package com.anyway.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.anyway.dubbo.interfaces.api.DemoService;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author: wang_hui
 * @date: 2019/1/9 上午10:06
 */
@Service(
        version = "${demo.service.version}"
)
public class DemoServiceImpl implements DemoService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(">>>>>>>>>>>>>");
        return String.format("[%s] : Hello, %s", serviceName, name);
    }

}
