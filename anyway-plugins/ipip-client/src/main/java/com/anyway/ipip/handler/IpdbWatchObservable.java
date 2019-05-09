package com.anyway.ipip.handler;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.File;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: wang_hui
 * @date: 2019/5/9 上午10:04
 */
public class IpdbWatchObservable extends Observable {

    private File file;
    private int interval;
    private long lastModified;

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1, new BasicThreadFactory.Builder().namingPattern("monitor-ipip-reload-%d").build());

    /**
     * @param filePath ipdb文件路径
     * @param interval 时间间隔，单位：seconds
     */
    public IpdbWatchObservable(String filePath, int interval) {
        this.file = new File(filePath);
        this.interval = interval;
        this.lastModified = file.lastModified();
    }

    public void execute() {
        executor.scheduleAtFixedRate(() -> {
            long l = file.lastModified();
            if (l != this.lastModified) {
                this.lastModified = l;
                try {
                    TimeUnit.SECONDS.sleep(10 * 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setChanged();
                notifyObservers();
            }
        }, 10, interval, TimeUnit.SECONDS);
    }

}
