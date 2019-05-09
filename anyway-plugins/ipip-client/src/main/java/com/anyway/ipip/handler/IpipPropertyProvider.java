package com.anyway.ipip.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author: wang_hui
 * @date: 2019/5/9 上午11:14
 */
@Slf4j
public class IpipPropertyProvider {
    /**
     * ipdb地址库路径
     */
    private static String filePath;
    /**
     * 加载间隔时间，单位：seconds
     */
    private static int interval;

    private static final String DEFAULT_FILE_PATH = "/data/ipip/17monipdb/ipip.ipdb";
    private static final int DEFAULT_RELOAD_INTERVAL = 3600;

    public IpipPropertyProvider() {
    }

    public IpipPropertyProvider(String filePath, int interval) {
        IpipPropertyProvider.filePath = filePath;
        IpipPropertyProvider.interval = interval;
    }

    @PostConstruct
    public void init() {
        initFilePath();
        initReloadInterval();
    }

    public static String getFilePath() {
        return filePath;
    }

    public static int getReloadInterval() {
        return interval;
    }

    private void initFilePath() {
        if (StringUtils.isEmpty(filePath)) {
            filePath = System.getProperty("ipip.file");
            if (!StringUtils.isEmpty(filePath)) {
                log.info("the file path of ipip data is set by JVM property: {}", filePath);
            } else {
                filePath = DEFAULT_FILE_PATH;
                log.info("the file path of ipip data is set by DEFAULT: {}", filePath);
            }
        } else {
            log.info("the file path of ipip data is set: {}", filePath);
        }
        File file = new File(filePath);
        if (file.exists() && file.canRead()) {
            return;
        } else {
            log.error("ipip data file is not found.");
            throw new RuntimeException("ipip data file can not find");
        }
    }

    private void initReloadInterval() {
        if (interval > 0) {
            log.info("the reload interval of ipip data is set: {}s", interval);
            return;
        }
        String intervalStr = System.getProperty("ipip.interval");
        if (!StringUtils.isEmpty(intervalStr)) {
            interval = Integer.parseInt(intervalStr);
            log.info("the reload interval of ipip data is set by JVM property: {}s", interval);
        } else {
            interval = DEFAULT_RELOAD_INTERVAL;
            log.info("the reload interval of ipip data is set by DEFAULT: {}s", interval);
        }
    }

}
