package com.anyway.client;

import java.util.concurrent.TimeUnit;

/**
 * @author: wang_hui
 * @date: 2019/5/5 上午10:14
 */
public class MultiThread {
    public static void main(String[] args) {
        ThreadPoolConfig config = new ThreadPoolConfig();
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    CompletableFutureTest2 c = new CompletableFutureTest2();
                    c.test();
                }
            });
            t.start();
        }
    }
}
