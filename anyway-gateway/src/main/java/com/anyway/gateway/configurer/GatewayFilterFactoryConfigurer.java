package com.anyway.gateway.configurer;

import com.anyway.gateway.filter.factory.ElapsedGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器工厂注册配置
 *
 * @author: wang_hui
 * @date: 2018/10/23 下午6:25
 */
@Configuration
public class GatewayFilterFactoryConfigurer {

    @Bean
    public ElapsedGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new ElapsedGatewayFilterFactory();
    }
    
}
