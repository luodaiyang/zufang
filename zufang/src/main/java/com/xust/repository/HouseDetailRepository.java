package com.xust.repository;

import com.xust.entity.HouseDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 16:29 2019/5/3
 * @modified By:
 */
public interface HouseDetailRepository extends CrudRepository<HouseDetail,Long > {
    HouseDetail findByHouseId(Long houseId);

    List<HouseDetail> findAllByHouseIdIn(List<Long> houseIds);
}
