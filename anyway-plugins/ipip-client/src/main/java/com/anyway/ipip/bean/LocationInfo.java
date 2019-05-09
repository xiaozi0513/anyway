package com.anyway.ipip.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.ipip.ipdb.CityInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author: wang_hui
 * @date: 2019/5/8 下午9:19
 */
@Slf4j
@Data
public class LocationInfo implements Serializable {
    private static final long serialVersionUID = 1076077168384724584L;

    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String region;
    /**
     * 城市
     */
    private String city;
    /**
     * 区/县
     */
    private String county;
    /**
     * 互联网服务提供商
     */
    private String isp;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 纬度
     */
    private Double latitude;

    public LocationInfo(String country, String region, String city, String county, String isp) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.county = county;
        this.isp = isp;
    }

    public LocationInfo(CityInfo cityInfo) {
        this.country = cityInfo.getCountryName();
        this.region = cityInfo.getRegionName();
        this.city = cityInfo.getCityName();
        this.county = "";
        this.isp = cityInfo.getIspDomain();
        parseLongitudeAndLatitude(cityInfo);
    }

    private void parseLongitudeAndLatitude(CityInfo cityInfo) {
        String longitudeStr = cityInfo.getLongitude();
        String latitudeStr = cityInfo.getLatitude();
        if (StringUtils.isAnyEmpty(longitudeStr, latitudeStr)) {
            return;
        }

        try {
            this.longitude = Double.parseDouble(longitudeStr);
            this.latitude = Double.parseDouble(latitudeStr);
        } catch (Exception e) {
            log.warn("解析ip地址的经纬度异常", e);
        }
    }

    public static LocationInfo buidDefault() {
        return new LocationInfo("", "", "", "", "");
    }


}
