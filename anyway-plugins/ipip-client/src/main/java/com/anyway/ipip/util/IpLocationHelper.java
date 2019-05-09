package com.anyway.ipip.util;

import com.anyway.ipip.bean.LocationInfo;
import com.anyway.ipip.handler.AutoReloadObserver;
import lombok.extern.slf4j.Slf4j;
import net.ipip.ipdb.City;
import net.ipip.ipdb.CityInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: wang_hui
 * @date: 2019/5/8 下午9:27
 */
@Slf4j
public class IpLocationHelper {

    private static City city = null;

    static {
        try {
            AutoReloadObserver autoReloadObserver = new AutoReloadObserver();
            city = autoReloadObserver.getCity();
        } catch (Exception e) {
            log.error("init ipip database error", e);
        }
    }

    private static final String CHINESE_LANGUAGE = "CN";

    /**
     * 根据ip地址获取地域信息
     *
     * @param ip ip地址
     * @return 地域信息，精确到区/县
     */
    public static LocationInfo getIpLocation(String ip) {
        ip = StringUtils.trimToEmpty(ip);
        if (StringUtils.isEmpty(ip) || city == null) {
            return LocationInfo.buidDefault();
        }

        try {
            CityInfo cityInfo = city.findInfo(ip, CHINESE_LANGUAGE);
            return new LocationInfo(cityInfo);
        } catch (Exception e) {
            log.error("查找ip地址所在区域异常, ip={}", ip, e);
            return LocationInfo.buidDefault();
        }
    }

}
