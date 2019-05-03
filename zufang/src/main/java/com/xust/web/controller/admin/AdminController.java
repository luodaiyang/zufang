package com.xust.web.controller.admin;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.xust.base.ApiResponse;
import com.xust.entity.SupportAddress;
import com.xust.service.ServiceResult;
import com.xust.service.house.IAddressService;
import com.xust.service.house.IHouseService;
import com.xust.service.house.IQiNiuService;
import com.xust.web.dto.HouseDTO;
import com.xust.web.dto.QiNiuPutRet;
import com.xust.web.dto.SupportAddressDTO;
import com.xust.web.form.HouseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 16:13 2019/4/24
 * @modified By:
 */
@Controller
public class AdminController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IQiNiuService qiNiuService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private Gson gson;

    /**
     *  
     * 后台管理中心
     * @author Luo Daiyang
     * @date 2019/4/24 16:18
     * @param []
     * @return java.lang.String
     */
    @GetMapping("/admin/center")
    public String adminCenterPage(){
        return "admin/center";
    }

    /**
     *  
     * 欢迎页
     * @author Luo Daiyang
     * @date 2019/4/24 16:20
     * @param []
     * @return java.lang.String
     */
    @GetMapping("/admin/welcome")
    public String welcomePage(){
        return "admin/welcome";
    }
    /**
     *  
     * 管理员登陆页
     * @author Luo Daiyang
     * @date 2019/4/24 16:53
     * @param []
     * @return java.lang.String
     */
    @GetMapping("/admin/login")
    public String adminLoginPage(){
        return "admin/login";
    }
    /**
     *  
     * 房源列表页
     * @author Luo Daiyang
     * @date 2019/4/29 11:15
     * @param []
     * @return java.lang.String
     */
    @GetMapping("admin/house/list")
    public String houseListPage() {
        return "admin/house-list";
    }

    /**
     *  
     * 新增房源功能页
     * @author Luo Daiyang
     * @date 2019/4/27 9:15
     * @param []
     * @return java.lang.String
     */
    @GetMapping("/admin/add/house")
    public String addHousePage(){
        return  "admin/house-add";
    }
   
    
    /**
     *  
     * 上传图片接口
     * @author Luo Daiyang
     * @date 2019/4/27 16:02
     * @param [file]
     * @return ApiResponse
     */
    @PostMapping(value = "admin/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ApiResponse uploadPhoto(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }

        String fileName = file.getOriginalFilename();

        try {
            InputStream inputStream = file.getInputStream();
            Response response = qiNiuService.uploadFile(inputStream);
            if (response.isOK()) {
                QiNiuPutRet ret = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
                return ApiResponse.ofSuccess(ret);
            } else {
                return ApiResponse.ofMessage(response.statusCode, response.getInfo());
            }

        } catch (QiniuException e) {
            Response response = e.response;
            try {
                return ApiResponse.ofMessage(response.statusCode, response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
                return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *  
     * 新增房源接口
     * @author Luo Daiyang
     * @date 2019/5/3 17:34
     * @param [houseForm, bindingResult]
     * @return com.xust.base.ApiResponse
     */

    @PostMapping("admin/add/house")
    @ResponseBody
    public ApiResponse addHouse(@Valid @ModelAttribute("form-house-add") HouseForm houseForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }

        if (houseForm.getPhotos() == null || houseForm.getCover() == null) {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), "必须上传图片");
        }

        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(houseForm.getCityEnName(), houseForm.getRegionEnName());
        if (addressMap.keySet().size() != 2) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }

        ServiceResult<HouseDTO> result = houseService.save(houseForm);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess(result.getResult());
        }

        return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
    }




}
