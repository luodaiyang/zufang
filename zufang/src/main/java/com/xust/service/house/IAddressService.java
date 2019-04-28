package com.xust.service.house;

import com.xust.entity.SupportAddress;
import com.xust.service.ServiceMultiResult;
import com.xust.web.dto.SupportAddressDTO;

import java.util.Map;

/**
 * 地址服务接口
 * @author: Luo Daiyang
 * @description:
 * @date Created in 18:39 2019/4/28
 * @modified By:
 */

public interface IAddressService {
    /**
     *  
     * 获取所有支持的城市列表
     * @author Luo Daiyang
     * @date 2019/4/28 18:47
     * @param []
     * @return com.xust.service.ServiceMultiResult<com.xust.web.dto.SupportAddressDTO>
     */
    ServiceMultiResult<SupportAddressDTO> findAllCities();

/**
 *
 *根据英文简写获取具体区域的信息
 * @author Luo Daiyang
 * @date 2019/4/28 18:48
 * @param [cityEnName, regionEnName]
 * @return java.util.Map<com.xust.entity.SupportAddress.Level,com.xust.web.dto.SupportAddressDTO>
 */
    Map<SupportAddress.Level, SupportAddressDTO>
    findCityAndRegion(String cityEnName, String regionEnName);

    /**
     *  
     * 根据城市英文简写获取该城市所有支持的区域信息
     * @author Luo Daiyang
     * @date 2019/4/28 18:49
     * @param [cityName]
     * @return com.xust.service.ServiceMultiResult
     */
    ServiceMultiResult findAllRegionsByCityName(String cityName);
}

