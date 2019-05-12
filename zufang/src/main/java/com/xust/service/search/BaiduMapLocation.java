package com.xust.service.search;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 百度位置信息
 * @author: Luo Daiyang
 * @description:
 * @date Created in 14:34 2019/5/11
 * @modified By:
 */
public class BaiduMapLocation {
    //经度
    @JsonProperty("lon")
    private  double longitude;

    //纬度
    @JsonProperty("lat")
    private  double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
