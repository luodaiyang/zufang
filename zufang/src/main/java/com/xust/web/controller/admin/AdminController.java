package com.xust.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 16:13 2019/4/24
 * @modified By:
 */
@Controller
public class AdminController {
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


}
