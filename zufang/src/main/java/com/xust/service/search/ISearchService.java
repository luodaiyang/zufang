package com.xust.service.search;

import com.xust.service.ServiceMultiResult;
import com.xust.service.ServiceResult;
import com.xust.web.form.RentSearch;

import java.util.List;

/**
 * 检索接口
 * @author: Luo Daiyang
 * @description:
 * @date Created in 15:36 2019/5/8
 * @modified By:
 */
public interface ISearchService {
   /**
    *
    * 索引目标房源
    * @author Luo Daiyang
    * @date 2019/5/8 15:38
    * @param [houseId]
    * @return void
    */
    boolean index(Long houseId);

/**
 *
 * 删除房源索引
 * @author Luo Daiyang
 * @date 2019/5/8 15:38
 * @param [houseId]
 * @return void
 */
    boolean remove(Long houseId);


    /**
     *  查询房源接口
     *
     * @author Luo Daiyang
     * @date 2019/5/9 16:22
     * @param [rentSearch]
     * @return com.xust.service.ServiceMultiResult<java.lang.Long>
     */
    ServiceMultiResult<Long> query(RentSearch rentSearch);

    /**
     *
     * 获取补全建议关键词
     * @author Luo Daiyang
     * @date 2019/5/10 11:06
     * @param [prefix]
     * @return com.xust.service.ServiceResult<java.util.List<java.lang.String>>
     */
    ServiceResult<List<String>> suggest(String prefix);



    /**
     * 聚合特定小区的房间数
     */
    ServiceResult<Long> aggregateDistrictHouse(String cityEnName, String regionEnName, String district);

}
