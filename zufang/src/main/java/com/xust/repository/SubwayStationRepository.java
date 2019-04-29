package com.xust.repository;

import com.xust.entity.SubwayStation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 10:33 2019/4/29
 * @modified By:
 */
public interface SubwayStationRepository  extends CrudRepository<SubwayStation,Long>{
    List<SubwayStation> findAllBySubwayId(Long subwayId);
}
