package com.xust.service.search;

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
    void remove(Long houseId);
}
