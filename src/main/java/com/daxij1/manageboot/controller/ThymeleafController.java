package com.daxij1.manageboot.controller;

import com.daxij1.manageboot.pojo.dto.MenuDTO;
import com.daxij1.manageboot.pojo.entity.Menu;
import com.daxij1.manageboot.pojo.entity.User;
import com.daxij1.manageboot.service.MenuService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/26 13:48
 * @description：ThymeleafController
 */
@Controller
public class ThymeleafController {
    
    @Autowired
    private MenuService menuService;

    @RequestMapping(path = {"/", "/index.html"})
    public String index(ModelMap map, HttpSession session) {
        User user = (User) session.getAttribute("user");
        map.put("user", user);
        Pair<List<MenuDTO>, List<Menu>> pair = menuService.findUserMenuList(user.getId());
        List<MenuDTO> menuList = pair.getLeft();
        List<Menu> menuitemList = pair.getRight();
        map.put("menuList", menuList);
        map.put("menuitemList", menuitemList);
        // 默认展开第一个菜单目录
        // 多个用逗号隔开，如['1','3']
        String defaultOpenDir = "['" + menuList.get(0).getId() + "']";
        map.put("defaultOpenDir", defaultOpenDir);
        // 默认进入的模块
        map.put("defaultModule", menuList.get(0).getMenuList().get(0).getId());
        return "index";
    }

}
