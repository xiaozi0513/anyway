package com.anyway.ipip.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: wang_hui
 * @date: 2019/5/9 上午10:23
 */
@Slf4j
public class ThreadUtil {

    /**
     * 休眠
     *
     * @param millis 休眠时间，单位：毫秒
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.info("sleep error.", e);
        }
    }

}
