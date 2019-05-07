package com.xust.service.house;

import com.xust.service.ServiceMultiResult;
import com.xust.service.ServiceResult;
import com.xust.web.dto.HouseDTO;
import com.xust.web.form.DatatableSearch;
import com.xust.web.form.HouseForm;
import com.xust.web.form.RentSearch;

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
    /**
     *  
     * 查询房源完整信息
     * @author Luo Daiyang
     * @date 2019/5/4 15:28
     * @param [id]
     * @return com.xust.service.ServiceResult<com.xust.web.dto.HouseDTO>
     */
    ServiceResult<HouseDTO> findCompleteOne(Long id);

    ServiceResult update(HouseForm houseForm);

    ServiceMultiResult<HouseDTO> adminQuery(DatatableSearch searchBody);

    /**
     * 移除图片
     */
    ServiceResult removePhoto(Long id);

    /**
     * 更新封面
     */
    ServiceResult updateCover(Long coverId, Long targetId);

    /**
     * 新增标签
     */
    ServiceResult addTag(Long houseId, String tag);

    /**
     * 移除标签
     */
    ServiceResult removeTag(Long houseId, String tag);
    /**
     * 更新房源状态
     */
    ServiceResult updateStatus(Long id, int status);
    /**
     * 查询房源信息集
     */
    ServiceMultiResult<HouseDTO> query(RentSearch rentSearch);
}
