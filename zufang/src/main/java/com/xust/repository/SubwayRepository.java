package com.xust.repository;

import com.xust.entity.Subway;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 10:30 2019/4/29
 * @modified By:
 */
public interface SubwayRepository extends CrudRepository<Subway ,Long> {
    List<Subway> findAllByCityEnName(String cityEnName);
}
