package com.xust.repository;

import com.xust.entity.SupportAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 17:03 2019/4/28
 * @modified By:
 */
public interface SupportAddressRepository extends CrudRepository<SupportAddress,Long> {
    /**
     *  
     * 获取所有行政级别信息
     * @author Luo Daiyang
     * @date 2019/4/28 17:06
     * @param [level]
     * @return java.util.List<com.xust.entity.SupportAddress>
     */
    List<SupportAddress> findAllByLevel(String level);

    SupportAddress findByEnNameAndLevel(String enName, String level);


    List<SupportAddress> findAllByLevelAndBelongTo(String level, String belongTo);

    SupportAddress findByEnNameAndBelongTo(String enName, String belongTo);

}
