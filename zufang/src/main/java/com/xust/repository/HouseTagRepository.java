package com.xust.repository;

import com.xust.entity.HouseTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 16:22 2019/5/3
 * @modified By:
 */
public interface HouseTagRepository extends CrudRepository<HouseTag ,Long> {
    HouseTag findByNameAndHouseId(String name, Long houseId);

    List<HouseTag> findAllByHouseId(Long id);

    List<HouseTag> findAllByHouseIdIn(List<Long> houseIds);
}
