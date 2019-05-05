package com.anyway.common.utils;

import com.anyway.common.util.BaseThreadFactoryConfig;

/**
 * 配置自己需要的线程池，然后通过ThreadPoolExecutorFactory使用
 *
 * @author: wang_hui
 * @date: 2019/4/29 上午10:30
 */
public class ThreadPoolConfig extends BaseThreadFactoryConfig {

    private static final int COMMON_CORE_POOL_SIZE = 4 * Runtime.getRuntime().availableProcessors();
    private static final int COMMON_MAX_POOL_SIZE = 4 * Runtime.getRuntime().availableProcessors();
    private static final int COMMON_QUEUE_SIZE = 1000;

    public ThreadPoolConfig() {
    }

    public ThreadPoolConfig(String poolName, int corePoolSize, int maxPoolSize, int queueSize) {
        super(poolName, corePoolSize, maxPoolSize, queueSize);
    }

    public static final ThreadPoolConfig COMIC_SERVICE_POOL = new ThreadPoolConfig("comic_service_pool", COMMON_CORE_POOL_SIZE, COMMON_MAX_POOL_SIZE, COMMON_QUEUE_SIZE);
    public static final ThreadPoolConfig TEST_SERVICE_POOL = new ThreadPoolConfig("test_service_pool", COMMON_CORE_POOL_SIZE, COMMON_MAX_POOL_SIZE, COMMON_QUEUE_SIZE);

}
