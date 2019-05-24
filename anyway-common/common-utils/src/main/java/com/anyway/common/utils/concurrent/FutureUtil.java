package com.anyway.common.utils.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author: wang_hui
 * @date: 2019/4/30 下午2:05
 */
@Slf4j
public class FutureUtil {

    /**
     * 默认的超时时间
     */
    private static final long TIMEOUT = 1000L;

    /**
     * CompletableFuture自定义处理
     *
     * @param future       CompletableFuture
     * @param warnKey      异常方法
     * @param defaultValue 默认值
     * @return
     */
    public static <T> CompletableFuture<T> custom(CompletableFuture<T> future, String warnKey, T defaultValue) {
        return custom(future, TIMEOUT, TimeUnit.MILLISECONDS, warnKey, defaultValue);
    }

    /**
     * CompletableFuture自定义处理
     *
     * @param future       CompletableFuture
     * @param timeout      超时时间
     * @param unit         超时单位
     * @param warnKey      异常方法
     * @param defaultValue 默认值
     * @return
     */
    public static <T> CompletableFuture<T> custom(CompletableFuture<T> future, long timeout, TimeUnit unit, String warnKey, T defaultValue) {
        //超时判断
        Delayer.delay(future, timeout, unit);
        return future
                //异常处理
                .exceptionally(e -> {
                    if (e instanceof TimeoutException) {
                        log.error("[FutureUtil#custom]获取异步请求{}结果超时", warnKey, e);
                    } else {
                        log.error("[FutureUtil#custom]获取异步请求{}结果失败", warnKey, e);
                    }
                    return defaultValue;
                });
    }

    /**
     * 获取CompletableFuture异步执行结果<br>
     * 可配合使用FutureUtil.custom自定义超时时间和默认值
     *
     * @param future CompletableFuture
     * @return 异步执行结果，异常返回null
     */
    public static <T> T get(CompletableFuture<T> future) {
        if (future == null) {
            return null;
        }
        try {
            return future.get();
        } catch (Exception e) {
            log.error("[FutureUtil#get]获取异步请求结果异常", e);
            return null;
        }
    }

    /**
     * 获取CompletableFuture异步执行结果
     *
     * @param future       CompletableFuture
     * @param timeout      超时时间
     * @param unit         超时单位
     * @param warnKey      异常方法
     * @param defaultValue 默认值
     * @return 异步执行结果
     */
    public static <T> T getQuietly(CompletableFuture<T> future, long timeout, TimeUnit unit, String warnKey, T defaultValue) {
        if (future == null) {
            return defaultValue;
        }
        try {
            return future.get(timeout, unit);
        } catch (TimeoutException e) {
            log.error("[FutureUtil#getQuietly]获取异步请求{}结果超时", warnKey, e);
            return defaultValue;
        } catch (Exception e) {
            log.error("[FutureUtil#getQuietly]获取异步请求{}结果失败", warnKey, e);
            return defaultValue;
        }
    }

    /**
     * Singleton delay scheduler, used only for starting and cancelling tasks.
     */
    static final class Delayer {

        static ScheduledFuture<?> delay(CompletableFuture f, long timeout, TimeUnit unit) {
            return delayer.schedule(() -> {
                if (f != null && !f.isDone()) {
                    f.completeExceptionally(new TimeoutException("Timeout after " + timeout + " " + unit.name()));
                    f.cancel(false);
                }
            }, timeout, unit);
        }

        static final ScheduledThreadPoolExecutor delayer;

        static {
            delayer = new ScheduledThreadPoolExecutor(1, new DaemonThreadFactory());
            delayer.setRemoveOnCancelPolicy(true);
        }

        static final class DaemonThreadFactory implements ThreadFactory {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }
    }

}
