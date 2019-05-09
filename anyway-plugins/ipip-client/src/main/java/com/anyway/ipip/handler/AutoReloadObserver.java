package com.anyway.ipip.handler;

import com.anyway.ipip.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import net.ipip.ipdb.City;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * @author: wang_hui
 * @date: 2019/5/9 上午9:55
 */
@Slf4j
public class AutoReloadObserver implements Observer {

    private final String filePath;
    private City city;
    private IpdbWatchObservable observable;

    public AutoReloadObserver() throws IOException {
        this.filePath = IpipPropertyProvider.getFilePath();
        this.city = new City(filePath);
        this.observable = new IpdbWatchObservable(filePath, IpipPropertyProvider.getReloadInterval());
        this.observable.execute();
    }

    private static final int RELOAD_RETRY_TIMES = 3;

    @Override
    public void update(Observable o, Object arg) {
        int retry = RELOAD_RETRY_TIMES;
        while (retry-- > 0) {
            boolean success = this.city.reload(filePath);
            if (success) {
                log.info("ipip data has already been changed and reload.");
                break;
            }
            log.warn("ipip data reload error. start retrying, retry={}", retry);
            ThreadUtil.sleep(1000);
            if (retry == NumberUtils.INTEGER_ONE) {
                observable.deleteObserver(this);
                log.error("ipip data reload error! please check the file.");
            }
        }
    }

    /**
     * 获取city
     *
     * @return
     */
    public City getCity() {
        return city;
    }

}
