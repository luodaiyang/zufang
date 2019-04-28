package com.xust.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 18:43 2019/4/28
 * @modified By:
 */
public class SupportAddressDTO {
    private  long id;
    @JsonProperty(value = "belong_to")
    private String belongTO;

    @JsonProperty(value = "en_name")
    private String enName;

    @JsonProperty(value = "cn_name")
    private String cnName;

    private String level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBelongTO() {
        return belongTO;
    }

    public void setBelongTO(String belongTO) {
        this.belongTO = belongTO;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
