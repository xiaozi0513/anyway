package com.anyway.common.utils;

import com.anyway.common.util.ThreadPoolExecutorFactory;

/**
 * @author: wang_hui
 * @date: 2019/4/29 上午11:41
 */
public class ThreadPoolConfigTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            ThreadPoolExecutorFactory.acquire(ThreadPoolConfig.COMIC_SERVICE_POOL).execute(() -> {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(">>>>>>>>");
            });
        }
    }

}
