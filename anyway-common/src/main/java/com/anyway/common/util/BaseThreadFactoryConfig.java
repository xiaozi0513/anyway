package com.anyway.common.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础线程池定义
 *
 * @author: wang_hui
 * @date: 2019/4/29 上午10:24
 */
@Data
public class BaseThreadFactoryConfig {

    private static List<BaseThreadFactoryConfig> configs = new ArrayList<>();

    //线程池名
    private String poolName;
    //核心线程数
    private int corePoolSize;
    //最大线程数
    private int maxPoolSize;
    //缓冲队列大小
    private int queueSize;

    public BaseThreadFactoryConfig() {
    }

    public BaseThreadFactoryConfig(String poolName, int corePoolSize, int maxPoolSize, int queueSize) {
        this.poolName = poolName;
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.queueSize = queueSize;
        configs.add(this);
    }

    public List<BaseThreadFactoryConfig> values() {
        return configs;
    }

}
