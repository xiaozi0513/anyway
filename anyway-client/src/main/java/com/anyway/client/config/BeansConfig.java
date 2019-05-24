package com.anyway.client.config;

import com.anyway.common.utils.httpclient.HttpClientUtil;
import com.anyway.ipip.handler.IpipPropertyProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: wang_hui
 * @date: 2019/5/9 下午2:17
 */
@Configuration
public class BeansConfig {
    @Bean
    public IpipPropertyProvider ipipPropertyProvider() {
        return new IpipPropertyProvider("/Users/kuaikan/Downloads/17monipdb/ipipfree.ipdb", 20);
    }

    @Bean
    public HttpClientUtil httpClientUtil() {
        HttpClientUtil httpClient = new HttpClientUtil();
        httpClient.init();
        return httpClient;
    }
}
