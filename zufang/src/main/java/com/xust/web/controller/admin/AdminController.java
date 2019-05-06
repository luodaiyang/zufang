package com.xust.web.controller.admin;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.xust.base.ApiDataTableResponse;
import com.xust.base.ApiResponse;
import com.xust.base.HouseOperation;
import com.xust.base.HouseStatus;
import com.xust.entity.SupportAddress;
import com.xust.service.ServiceMultiResult;
import com.xust.service.ServiceResult;
import com.xust.service.house.IAddressService;
import com.xust.service.house.IHouseService;
import com.xust.service.house.IQiNiuService;
import com.xust.web.dto.*;
import com.xust.web.form.DatatableSearch;
import com.xust.web.form.HouseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     * 后台管理中心
     *
     * @param []
     * @return java.lang.String
     * @author Luo Daiyang
     * @date 2019/4/24 16:18
     */
    @GetMapping("/admin/center")
    public String adminCenterPage() {
        return "admin/center";
    }

    /**
     * 欢迎页
     *
     * @param []
     * @return java.lang.String
     * @author Luo Daiyang
     * @date 2019/4/24 16:20
     */
    @GetMapping("/admin/welcome")
    public String welcomePage() {
        return "admin/welcome";
    }

    /**
     * 管理员登陆页
     *
     * @param []
     * @return java.lang.String
     * @author Luo Daiyang
     * @date 2019/4/24 16:53
     */
    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "admin/login";
    }


    /**
     * Datatables接口开发
     *
     * @param [searchBody]
     * @return com.xust.base.ApiDataTableResponse
     * @author Luo Daiyang
     * @date 2019/5/4 9:18
     */
    @PostMapping("admin/houses")
    @ResponseBody
    public ApiDataTableResponse houses(@ModelAttribute DatatableSearch searchBody) {
        ServiceMultiResult<HouseDTO> result = houseService.adminQuery(searchBody);

        ApiDataTableResponse response = new ApiDataTableResponse(ApiResponse.Status.SUCCESS);
        response.setData(result.getResult());
        response.setRecordsFiltered(result.getTotal());
        response.setRecordsTotal(result.getTotal());

        response.setDraw(searchBody.getDraw());
        return response;
    }

    /**
     * 房源列表页
     *
     * @param []
     * @return java.lang.String
     * @author Luo Daiyang
     * @date 2019/4/29 11:15
     */
    @GetMapping("admin/house/list")
    public String houseListPage() {
        return "admin/house-list";
    }


    /**
     * 新增房源功能页
     *
     * @param []
     * @return java.lang.String
     * @author Luo Daiyang
     * @date 2019/4/27 9:15
     */
    @GetMapping("/admin/add/house")
    public String addHousePage() {
        return "admin/house-add";
    }


    /**
     * 上传图片接口
     *
     * @param [file]
     * @return ApiResponse
     * @author Luo Daiyang
     * @date 2019/4/27 16:02
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
     * 新增房源接口
     *
     * @param [houseForm, bindingResult]
     * @return com.xust.base.ApiResponse
     * @author Luo Daiyang
     * @date 2019/5/3 17:34
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


    /**
     * 房源信息编辑页
     *
     * @param [id, model]
     * @return java.lang.String
     * @author Luo Daiyang
     * @date 2019/5/4 15:22
     */
    @GetMapping("admin/house/edit")
    public String houseEditPage(@RequestParam(value = "id") Long id, Model model) {

        if (id == null || id < 1) {
            return "404";
        }

        ServiceResult<HouseDTO> serviceResult = houseService.findCompleteOne(id);
        if (!serviceResult.isSuccess()) {
            return "404";
        }

        HouseDTO result = serviceResult.getResult();
        model.addAttribute("house", result);

        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(result.getCityEnName(), result.getRegionEnName());
        model.addAttribute("city", addressMap.get(SupportAddress.Level.CITY));
        model.addAttribute("region", addressMap.get(SupportAddress.Level.REGION));

        HouseDetailDTO detailDTO = result.getHouseDetail();
        ServiceResult<SubwayDTO> subwayServiceResult = addressService.findSubway(detailDTO.getSubwayLineId());
        if (subwayServiceResult.isSuccess()) {
            model.addAttribute("subway", subwayServiceResult.getResult());
        }

        ServiceResult<SubwayStationDTO> subwayStationServiceResult = addressService.findSubwayStation(detailDTO.getSubwayStationId());
        if (subwayStationServiceResult.isSuccess()) {
            model.addAttribute("station", subwayStationServiceResult.getResult());
        }

        return "admin/house-edit";
    }

    /**
     * 编辑接口
     *
     * @param [houseForm, bindingResult]
     * @return com.xust.base.ApiResponse
     * @author Luo Daiyang
     * @date 2019/5/4 15:26
     */
    @PostMapping("admin/house/edit")
    @ResponseBody
    public ApiResponse saveHouse(@Valid @ModelAttribute("form-house-edit") HouseForm houseForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }

        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(houseForm.getCityEnName(), houseForm.getRegionEnName());

        if (addressMap.keySet().size() != 2) {
            return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
        }

        ServiceResult result = houseService.update(houseForm);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess(null);
        }

        ApiResponse response = ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        response.setMessage(result.getMessage());
        return response;
    }


    /**
     * 移除图片接口
     *
     * @param [id]
     * @return com.xust.base.ApiResponse
     * @author Luo Daiyang
     * @date 2019/5/4 15:54
     */

    @DeleteMapping("admin/house/photo")
    @ResponseBody
    public ApiResponse removeHousePhoto(@RequestParam(value = "id") Long id) {
        ServiceResult result = this.houseService.removePhoto(id);

        if (result.isSuccess()) {
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }
    }

    /**
     * 修改封面接口
     */
    @PostMapping("admin/house/cover")
    @ResponseBody
    public ApiResponse updateCover(@RequestParam(value = "cover_id") Long coverId,
                                   @RequestParam(value = "target_id") Long targetId) {
        ServiceResult result = this.houseService.updateCover(coverId, targetId);

        if (result.isSuccess()) {
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }
    }

    /**
     * 增加标签接口
     */
    @PostMapping("admin/house/tag")
    @ResponseBody
    public ApiResponse addHouseTag(@RequestParam(value = "house_id") Long houseId,
                                   @RequestParam(value = "tag") String tag) {
        if (houseId < 1 || Strings.isNullOrEmpty(tag)) {
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        ServiceResult result = this.houseService.addTag(houseId, tag);
        if (result.isSuccess()) {
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }
    }

    /**
     * 移除标签接口
     */
    @DeleteMapping("admin/house/tag")
    @ResponseBody
    public ApiResponse removeHouseTag(@RequestParam(value = "house_id") Long houseId,
                                      @RequestParam(value = "tag") String tag) {
        if (houseId < 1 || Strings.isNullOrEmpty(tag)) {
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        ServiceResult result = this.houseService.removeTag(houseId, tag);
        if (result.isSuccess()) {
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }


    }


/**
 *  
 * 审核接口
 * @author Luo Daiyang
 * @date 2019/5/6 15:15
 * @param [id, operation]
 * @return com.xust.base.ApiResponse
 */
    @PutMapping("admin/house/operate/{id}/{operation}")
    @ResponseBody
    public ApiResponse operateHouse(@PathVariable(value = "id") Long id,
                                    @PathVariable(value = "operation") int operation) {
        if (id <= 0) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        ServiceResult result;

        switch (operation) {
            case HouseOperation.PASS:
                result = this.houseService.updateStatus(id, HouseStatus.PASSES.getValue());
                break;
            case HouseOperation.PULL_OUT:
                result = this.houseService.updateStatus(id, HouseStatus.NOT_AUDITED.getValue());
                break;
            case HouseOperation.DELETE:
                result = this.houseService.updateStatus(id, HouseStatus.DELETED.getValue());
                break;
            case HouseOperation.RENT:
                result = this.houseService.updateStatus(id, HouseStatus.RENTED.getValue());
                break;
            default:
                return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        if (result.isSuccess()) {
            return ApiResponse.ofSuccess(null);
        }
        return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(),
                result.getMessage());
    }
}