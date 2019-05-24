package com.anyway.common.utils.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 创建线程池工厂<br>
 * Queue: 默认LinkedBlockingQueue
 *
 * @author: wang_hui
 * @date: 2019/4/29 上午10:35
 */
@Slf4j
public class ThreadPoolExecutorFactory {

    //要加载的服务线程池列表
    private static List<BaseThreadFactoryConfig> configs = new ArrayList<>();
    //线程池缓存
    private static Map<String, ThreadPoolExecutor> threadPoolCache = new HashMap<>();

    static {
        BaseThreadFactoryConfig threadFactoryConfig = new BaseThreadFactoryConfig();
        configs = threadFactoryConfig.values();
        initThreadPoolCache();
        monitorThreadPool();
        addShutdownHook();
    }

    public static ThreadPoolExecutor acquire(String poolName) {
        ThreadPoolExecutor executor = threadPoolCache.get(poolName);
        if (executor == null) {
            log.error("thread pool {} not initialized, use default thread pool.", poolName);
            executor = threadPoolCache.get(DEFAULT_POOL_NAME);
        }
        return executor;
    }

    public static ThreadPoolExecutor acquire(BaseThreadFactoryConfig config) {
        return acquire(config.getPoolName());
    }

    public static final String DEFAULT_POOL_NAME = "default";

    /**
     * 初始化线程池缓存
     */
    private static void initThreadPoolCache() {
        for (BaseThreadFactoryConfig config : configs) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(config.getCorePoolSize(), config.getMaxPoolSize(), 60,
                    TimeUnit.SECONDS, new LinkedBlockingDeque<>(config.getQueueSize()), new BasicThreadFactory.Builder().namingPattern(config.getPoolName() + "-%d").build());
            threadPoolCache.put(config.getPoolName(), threadPoolExecutor);
        }
        ThreadPoolExecutor defaultPool = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1000), new BasicThreadFactory.Builder().namingPattern(DEFAULT_POOL_NAME + "-%d").build());
        threadPoolCache.put(DEFAULT_POOL_NAME, defaultPool);
    }

    private static final String COMMA = ",";

    /**
     * 监控线程池状态
     */
    private static void monitorThreadPool() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("THREAD_POOL_SCHEDULE_MONITOR-%d").build());
        executorService.scheduleAtFixedRate(() -> {
            String logInfo = StringUtils.EMPTY;
            for (Map.Entry<String, ThreadPoolExecutor> entry : threadPoolCache.entrySet()) {
                logInfo = StringUtils.join(logInfo, entry.getKey(), "[CORE_SIZE=", entry.getValue().getCorePoolSize(), ",REAL_SIZE=", entry.getValue().getPoolSize(), ",QUEUE_SIZE=", entry.getValue().getQueue().size(), "]", COMMA);
            }
            log.debug(StringUtils.removeEnd(logInfo, COMMA));
        }, 60, 60, TimeUnit.SECONDS);
    }

    /**
     * 添加线程池关闭钩子
     */
    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Map.Entry<String, ThreadPoolExecutor> entry : threadPoolCache.entrySet()) {
                close(entry.getValue());
            }
        }));
    }

    private static void close(ThreadPoolExecutor executor) {
        if (executor != null) {
            int tryTime = 3;
            executor.shutdown();
            while (tryTime-- > 0) {
                try {
                    executor.awaitTermination(1, TimeUnit.SECONDS);
                    if (executor.isTerminated()) {
                        break;
                    }
                } catch (InterruptedException e) {
                    log.error("thread executor terminate error.", e);
                }
            }
        }
    }

}
