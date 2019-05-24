package com.anyway.client;

import com.anyway.common.utils.concurrent.ThreadPoolExecutorFactory;
import com.anyway.common.utils.concurrent.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: wang_hui
 * @date: 2019/4/29 上午11:41
 */
@Slf4j
public class ThreadPoolConfigTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            ThreadPoolExecutorFactory.acquire(ThreadPoolConfig.COMIC_SERVICE_POOL).execute(() -> {
                ThreadUtil.sleep(1000);
                log.info(">>>>>>>>");
            });
        }
    }

}
