package com.xust.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 11:01 2019/4/23
 * @modified By:
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String   index(Model model){
        model.addAttribute("name" ,"太阳");
        return "index";
    }
}
