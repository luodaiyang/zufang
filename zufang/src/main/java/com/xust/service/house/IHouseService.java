package com.xust.service.house;

import com.xust.service.ServiceResult;
import com.xust.web.dto.HouseDTO;
import com.xust.web.form.HouseForm;

/**
 * 房屋管理服务接口
 * @author: Luo Daiyang
 * @description:
 * @date Created in 16:43 2019/5/3
 * @modified By:
 */
public interface IHouseService  {

    /**
     *  
     * 新增
     * @author Luo Daiyang
     * @date 2019/5/3 16:51
     * @param [houseForm]
     * @return com.xust.service.ServiceResult<com.xust.web.dto.HouseDTO>
     */
    ServiceResult<HouseDTO> save(HouseForm houseForm);


}
