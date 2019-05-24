package com.anyway.client;

import com.anyway.common.utils.concurrent.FutureUtil;
import com.anyway.common.utils.concurrent.ThreadPoolExecutorFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author: wang_hui
 * @date: 2019/4/30 上午10:08
 */
@Slf4j
public class CompletableFutureTest2 {

    public void test() {
        //示例一
        CompletableFuture<String> future1 = method1();
        CompletableFuture<String> future2 = method2();
//        String str1 = FutureUtil.getQuietly(future1, 1000, TimeUnit.MILLISECONDS, "method1", "default method1");
//        log.info("str1: {}", str1);
//        String str2 = FutureUtil.getQuietly(future2, 1000, TimeUnit.MILLISECONDS, "method2", "default method2");
//        log.info("str2: {}", str2);

        //示例二
        List<String> list = new ArrayList<>();
        CompletableFuture f1 = FutureUtil.custom(future1, 2000, TimeUnit.MILLISECONDS, "method1", "df1").thenAccept(list::add);
        CompletableFuture f2 = FutureUtil.custom(future2, 2000, TimeUnit.MILLISECONDS, "method2", "df2").thenAccept(list::add);
        CompletableFuture.allOf(f1, f2).join();
        log.info("{}, {}", list.get(0), list.get(1));

    }

    public CompletableFuture<String> method1() {
        return CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            int a = 1 / 0;
            log.info(">>>>>>>>>>>>>{}", "async method1");
            return "future1";
        }, ThreadPoolExecutorFactory.acquire(ThreadPoolConfig.TEST_SERVICE_POOL));
    }

    public CompletableFuture<String> method2() {
        return CompletableFuture.supplyAsync(() -> {
            log.info(">>>>>>>>>>>>>{}", "async method2");
            return "future2";
        }, ThreadPoolExecutorFactory.acquire(ThreadPoolConfig.COMIC_SERVICE_POOL));
    }

}
