package com.xust.service.search;

import com.xust.service.ServiceMultiResult;
import com.xust.web.form.RentSearch;

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
}
