package com.daxij1.manageboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daxij1.manageboot.mapper.MenuMapper;
import com.daxij1.manageboot.pojo.dto.MenuDTO;
import com.daxij1.manageboot.pojo.entity.Menu;
import com.daxij1.manageboot.pojo.vo.MenuTreeVO;
import com.daxij1.manageboot.service.MenuService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public Pair<List<MenuDTO>, List<Menu>> findUserMenuList(long userid) {
        List<MenuDTO> menuTreeList = new ArrayList<>();
        // 1.查询一级菜单目录
        List<Menu> firstMenuList = getBaseMapper().selectListByUserid(userid, 1);
        for (Menu menu : firstMenuList) {
            MenuDTO menuDTO = new MenuDTO();
            BeanUtils.copyProperties(menu, menuDTO);
            menuTreeList.add(menuDTO);
        }
        // 2.查询二级菜单项
        List<Menu> secondMenuList = getBaseMapper().selectListByUserid(userid, 2);
        Map<Integer, List<Menu>> secondGroupMenuMap = secondMenuList.stream().collect(Collectors.groupingBy(Menu::getParentid));
        // 3.将二级菜单项设置为一级菜单目录子菜单属性
        for (MenuDTO menuDTO : menuTreeList) {
            menuDTO.setMenuList(secondGroupMenuMap.get(menuDTO.getId()));
        }
        return ImmutablePair.of(menuTreeList, secondMenuList);
    }

    @Override
    public List<MenuTreeVO> treeMenuList() {
        List<MenuTreeVO> menuTreeVOList = new ArrayList<>();
        List<Menu> firstMenuList = getBaseMapper().selectListByLevel(1);
        for (Menu menu : firstMenuList) {
            MenuTreeVO menuTreeVO = new MenuTreeVO();
            BeanUtils.copyProperties(menu, menuTreeVO);
            menuTreeVOList.add(menuTreeVO);
        }
        List<Menu> secondMenuList = getBaseMapper().selectListByLevel(2);
        Map<Integer, List<Menu>> secondGroupMenuMap = secondMenuList.stream().collect(Collectors.groupingBy(Menu::getParentid));
        for (MenuTreeVO menuTreeVO : menuTreeVOList) {
            List<Menu> menuList = secondGroupMenuMap.get(menuTreeVO.getId());
            List<MenuTreeVO> childMenuTreeVOList = new ArrayList<>();
            for (Menu menu : menuList) {
                MenuTreeVO item = new MenuTreeVO();
                BeanUtils.copyProperties(menu, item);
                childMenuTreeVOList.add(item);
            }
            menuTreeVO.setChildren(childMenuTreeVOList);
        }
        return menuTreeVOList;
    }

    @Override
    public List<Menu> findByLevel(Integer level) {
        return getBaseMapper().selectListByLevel(level);
    }

}
