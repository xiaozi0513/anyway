package com.anyway.common.utils.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: wang_hui
 * @date: 2019/5/22 下午8:28
 */
@Slf4j
public class ThreadUtil {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error("thread sleep error.", e);
        }
    }

}
