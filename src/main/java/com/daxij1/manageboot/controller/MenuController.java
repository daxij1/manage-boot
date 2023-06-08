package com.daxij1.manageboot.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daxij1.manageboot.framework.annotation.Auth;
import com.daxij1.manageboot.framework.pojo.ResponseVO;
import com.daxij1.manageboot.pojo.entity.Menu;
import com.daxij1.manageboot.pojo.param.MenuAddOrUpdateParam;
import com.daxij1.manageboot.pojo.param.MenuQueryParam;
import com.daxij1.manageboot.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/8 10:12
 * @description：MenuController
 */
@RestController
@RequestMapping("/menu")
@DS("manageboot")
@Auth(role = "系统管理员")
public class MenuController {
    
    @Autowired
    private MenuService menuService;

    @PostMapping("/list")
    public ResponseVO<PageInfo<Menu>> list(@Valid @RequestBody MenuQueryParam param){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (param.getLevel() != null) {
            queryWrapper.eq("level", param.getLevel());
        }
        if (StringUtils.isNotEmpty(param.getName())) {
            queryWrapper.like("name", "%" + param.getName() + "%");
        }
        PageHelper.startPage(param.getPageno(), param.getPagesize());
        List<Menu> list = menuService.list(queryWrapper);
        return ResponseVO.success(new PageInfo<>(list));
    }

    @PostMapping("/addOrUpdate")
    public ResponseVO<Object> addOrUpdate(@Valid @RequestBody MenuAddOrUpdateParam param){
        Integer id = param.getId();
        Menu menu = new Menu();
        BeanUtils.copyProperties(param, menu);
        if (id != null) { //更新
            menu.setLastmodifiedtime(new Date());
            menuService.updateById(menu);
        } else { //新增
            menuService.save(menu);
        }
        return ResponseVO.success();
    }

    @GetMapping("/delete")
    public ResponseVO<Object> delete(Integer id){
        if (id == null || id <= 0) {
            return ResponseVO.paramFail("菜单id有误");
        }
        Menu menu = new Menu();
        menu.setId(id);
        menu.setDel(1);
        menuService.updateById(menu);
        return ResponseVO.success();
    }
    
}
