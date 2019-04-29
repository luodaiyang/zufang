package com.xust.web.dto;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 10:35 2019/4/29
 * @modified By:
 */
public class SubwayDTO  {

    private  Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }

    private String name;
    private  String cityEnName;
}
