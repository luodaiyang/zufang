package com.xust.web.controller.house;

import com.xust.base.ApiResponse;
import com.xust.service.ServiceMultiResult;
import com.xust.service.ServiceResult;
import com.xust.service.house.IAddressService;
import com.xust.service.user.IUserService;
import com.xust.web.dto.SupportAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 16:41 2019/4/28
 * @modified By:
 */
@Controller
public class HouseController {

    @Autowired
    private IAddressService addressService;


    @Autowired
    private IUserService userService;
    
    /**
     *  
     * 获取支持城市列表
     * @author Luo Daiyang
     * @date 2019/4/28 16:46
     * @param []
     * @return com.xust.base.ApiResponse
     */
    @GetMapping("address/support/cities")
    @ResponseBody
    public ApiResponse getSupportCities(){
        ServiceMultiResult<SupportAddressDTO >
                result=addressService.findAllCities();
        if(result.getResultSize()==0){
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(result.getResult());
    }

    
    /**
     *  
     * 获取对应城市支持区域列表
     * @author Luo Daiyang
     * @date 2019/4/28 19:41
     * @param [cityEnName]
     * @return com.xust.base.ApiResponse
     */
    @GetMapping("address/support/regions")
    @ResponseBody
    public ApiResponse getSupportRegions(@RequestParam(name = "city_name") String cityEnName) {
        ServiceMultiResult<SupportAddressDTO> addressResult = addressService.findAllRegionsByCityName(cityEnName);
        if (addressResult.getResult() == null || addressResult.getTotal() < 1) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(addressResult.getResult());
    }


}
