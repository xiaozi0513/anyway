package com.anyway.dubbo.interfaces.api;

/**
 * @author: wang_hui
 * @date: 2019/1/9 上午10:01
 */
public interface DemoService {

    /**
     * 返回问候语
     *
     * @param name 姓名
     * @return 问候语
     */
    String sayHello(String name);
    
}
