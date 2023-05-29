package com.daxij1.manageboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/26 13:48
 * @description：ThymeleafController
 */
@Controller
public class ThymeleafController {
    
    @Value("${spring.profiles.active}")
    private String env;

    @RequestMapping("/")
    public String index(ModelMap map){
        map.put("env", env);
        return "index";
    }

    @RequestMapping("/index.html")
    public String html(){
        return "index";
    }
    
}
