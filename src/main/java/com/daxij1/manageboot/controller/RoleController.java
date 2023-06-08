package com.daxij1.manageboot.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daxij1.manageboot.framework.annotation.Auth;
import com.daxij1.manageboot.framework.pojo.ResponseVO;
import com.daxij1.manageboot.pojo.entity.Role;
import com.daxij1.manageboot.pojo.param.RoleAddOrUpdateParam;
import com.daxij1.manageboot.pojo.param.RoleQueryParam;
import com.daxij1.manageboot.service.RoleService;
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
 * @date ：Created in 2023/6/8 15:59
 * @description：RoleController
 */
@RestController
@RequestMapping("/role")
@DS("manageboot")
@Auth(role = "超级管理员")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @PostMapping("/list")
    public ResponseVO<PageInfo<Role>> list(@Valid @RequestBody RoleQueryParam param){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getName())) {
            queryWrapper.like("name", "%" + param.getName() + "%");
        }
        PageHelper.startPage(param.getPageno(), param.getPagesize());
        List<Role> list = roleService.list(queryWrapper);
        return ResponseVO.success(new PageInfo<>(list));
    }

    @PostMapping("/addOrUpdate")
    public ResponseVO<Object> addOrUpdate(@Valid @RequestBody RoleAddOrUpdateParam param){
        Integer id = param.getId();
        Role role = new Role();
        BeanUtils.copyProperties(param, role);
        if (id != null) { //更新
            role.setLastmodifiedtime(new Date());
            roleService.updateById(role);
        } else { //新增
            roleService.save(role);
        }
        return ResponseVO.success();
    }

    @GetMapping("/delete")
    public ResponseVO<Object> delete(Integer id){
        if (id == null || id <= 0) {
            return ResponseVO.paramFail("菜单id有误");
        }
        roleService.removeById(id);
        return ResponseVO.success();
    }
    
}
