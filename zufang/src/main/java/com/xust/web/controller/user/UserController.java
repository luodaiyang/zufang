package com.xust.web.controller.user;

import com.xust.base.ApiResponse;
import com.xust.base.LoginUserUtil;
import com.xust.service.ServiceResult;
import com.xust.service.user.IUserService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 17:08 2019/4/26
 * @modified By:
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;



   @GetMapping("/user/login")
    public  String loginPage(){
       return  "user/login";
   }

   @GetMapping("user/center")
    public  String  centerPage(){
       return  "user/center";
   }



    @PostMapping(value = "api/user/info")
    @ResponseBody
    public ApiResponse updateUserInfo(@RequestParam(value = "profile") String profile,
                                      @RequestParam(value = "value") String value) {
        if (value.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        if ("email".equals(profile) && !LoginUserUtil.checkEmail(value)) {
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST, "不支持的邮箱格式");
        }


        ServiceResult result = userService.modifyUserProfile(profile, value);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess("");
        } else {
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST, result.getMessage());
        }

    }
}