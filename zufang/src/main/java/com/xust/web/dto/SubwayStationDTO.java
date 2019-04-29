package com.xust.web.dto;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 10:37 2019/4/29
 * @modified By:
 */
public class SubwayStationDTO {

    private Long id;

    private  Long subwayId;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(Long subwayId) {
        this.subwayId = subwayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
