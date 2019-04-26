package com.xust.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 17:08 2019/4/26
 * @modified By:
 */
@Controller
public class UserController {
   @GetMapping("/user/login")
    public  String loginPage(){
       return  "user/login";
   }

   @GetMapping("user/center")
    public  String  centerPage(){
       return  "user/center";
   }
}
