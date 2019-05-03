package com.xust.repository;

import com.xust.entity.HousePicture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 16:27 2019/5/3
 * @modified By:
 */
public interface HousePictureRepository  extends CrudRepository<HousePicture ,Long>{

    List<HousePicture> findAllByHouseId(Long id);
}
