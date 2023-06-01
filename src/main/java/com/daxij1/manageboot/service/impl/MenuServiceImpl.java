package com.daxij1.manageboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daxij1.manageboot.mapper.MenuMapper;
import com.daxij1.manageboot.pojo.dto.MenuDTO;
import com.daxij1.manageboot.pojo.entity.Menu;
import com.daxij1.manageboot.service.MenuService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.tuple.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/31 15:54
 * @description：MenuServiceImpl
 */
@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuMapper menuMapper;
    
    @Override
    public Pair<List<MenuDTO>, List<Menu>> findUserMenuList(long userid) {
        List<MenuDTO> left = new ArrayList<>();
        // 1.查询一级菜单目录
        QueryWrapper<Menu> firstQueryWrapper = new QueryWrapper<>();
        firstQueryWrapper.eq("level", 1);
        firstQueryWrapper.eq("active", 1);
        firstQueryWrapper.orderByAsc("sort");
        List<Menu> firstMenuList = menuMapper.selectList(firstQueryWrapper);
        for (Menu menu : firstMenuList) {
            MenuDTO menuDTO = new MenuDTO();
            BeanUtils.copyProperties(menu, menuDTO);
            left.add(menuDTO);
        }
        // 2.查询二级菜单项
        QueryWrapper<Menu> secondQueryWrapper = new QueryWrapper<>();
        secondQueryWrapper.eq("level", 2);
        secondQueryWrapper.eq("active", 1);
        secondQueryWrapper.orderByAsc("sort");
        List<Menu> right = menuMapper.selectList(secondQueryWrapper);
        Map<Integer, List<Menu>> secondGroupMenuMap = right.stream().collect(Collectors.groupingBy(Menu::getParentid));
        // 3.将二级菜单项设置为一级菜单目录子菜单属性
        for (MenuDTO menuDTO : left) {
            menuDTO.setMenuList(secondGroupMenuMap.get(menuDTO.getId()));
        }
        return ImmutablePair.of(left, right);
    }
    
}
