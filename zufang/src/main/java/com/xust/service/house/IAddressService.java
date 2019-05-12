package com.xust.service.house;

import com.xust.entity.SupportAddress;
import com.xust.service.ServiceMultiResult;
import com.xust.service.ServiceResult;
import com.xust.service.search.BaiduMapLocation;
import com.xust.web.dto.SubwayDTO;
import com.xust.web.dto.SubwayStationDTO;
import com.xust.web.dto.SupportAddressDTO;

import java.util.List;
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

    /**
     *  
     * 获取该城市所有的地铁线路
     * @author Luo Daiyang
     * @date 2019/4/29 10:45
     * @param [cityEnName]
     * @return java.util.List<com.xust.web.dto.SubwayDTO>
     */
    List<SubwayDTO> findAllSubwayByCity(String cityEnName);
    /**
     *  
     * 获取地铁线路所有的站点
     * @author Luo Daiyang
     * @date 2019/4/29 10:46
     * @param [subwayId]
     * @return java.util.List<com.xust.web.dto.SubwayStationDTO>
     */
    List<SubwayStationDTO> findAllStationBySubway(Long subwayId);
    /**
     *  
     * 获取地铁线信息
     * @author Luo Daiyang
     * @date 2019/4/29 10:47
     * @param [subwayId]
     * @return ServiceResult<SubwayDTO>
     */
    ServiceResult<SubwayDTO> findSubway(Long subwayId);
    
    /**
     *  
     * 获取地铁站点信息
     * @author Luo Daiyang
     * @date 2019/4/29 10:48
     * @param [stationId]
     * @return com.xust.service.ServiceResult<com.xust.web.dto.SubwayStationDTO>
     */

    ServiceResult<SubwayStationDTO> findSubwayStation(Long stationId);
    /**
     *  
     * 根据城市英文简写获取城市详细信息
     * @author Luo Daiyang
     * @date 2019/4/29 10:50
     * @param [cityEnName]
     * @return com.xust.service.ServiceResult<com.xust.web.dto.SupportAddressDTO>
     */
    ServiceResult<SupportAddressDTO> findCity(String cityEnName);


    /**
     * 根据城市以及具体地位获取百度地图的经纬度
     */
    ServiceResult<BaiduMapLocation> getBaiduMapLocation(String city, String address);

    /**
     * 上传百度LBS数据
     */
    ServiceResult lbsUpload(BaiduMapLocation location, String title, String address,
                            long houseId, int price, int area);

    /**
     * 移除百度LBS数据
     */
    ServiceResult removeLbs(Long houseId);
}

