package com.daxij1.manageboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daxij1.manageboot.mapper.AuthMapper;
import com.daxij1.manageboot.pojo.entity.Auth;
import com.daxij1.manageboot.pojo.entity.Menu;
import com.daxij1.manageboot.pojo.param.AuthUpdateParam;
import com.daxij1.manageboot.service.AuthService;
import com.daxij1.manageboot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthService {

    @Autowired
    private MenuService menuService;

    @Transactional
    @Override
    public void update(AuthUpdateParam param) {
        Integer roleid = param.getRoleid();
        List<Integer> menuIds = param.getMenuIds();
        List<Auth> authList = getAuthByRoleid(roleid);
        for (Auth auth : authList) {
            if (!menuIds.contains(auth.getMenuid())) {
                getBaseMapper().deleteById(auth.getId());
            }
        }
        List<Integer> ownMenuIds = authList.stream().map(Auth::getMenuid).collect(Collectors.toList());
        for (Integer menuId : menuIds) {
            if (!ownMenuIds.contains(menuId)) {
                Auth auth = new Auth();
                auth.setRoleid(roleid);
                auth.setMenuid(menuId);
                getBaseMapper().insert(auth);
            }
        }
    }

    @Override
    public List<Auth> getAuthByRoleid(Integer roleid) {
        QueryWrapper<Auth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleid", roleid);
        return getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public List<Integer> roleOwnMenuIdList(Integer roleid) {
        List<Integer> resultList = new ArrayList<>();
        // 1.查询当前角色绑定了哪些菜单id
        Set<Integer> authMenuIds = this.getAuthByRoleid(roleid).stream()
                .map(Auth::getMenuid)
                .collect(Collectors.toSet());
        // 2.适配前端，若二级菜单未全选中，则一级菜单不应选中，但后端却是存储了一级菜单，以下即为移除这部分一级菜单
        // 2.1 查询所有二级菜单
        List<Menu> secondMenuList = menuService.findByLevel(2);
        // 2.2 封装为"一级菜单id->二级菜单ids"的map结构
        Map<Integer, List<Integer>> menuIdToChirdMap = new HashMap<>();
        for (Menu menu : secondMenuList) {
            List<Integer> chirdList;
            if (menuIdToChirdMap.containsKey(menu.getParentid())) {
                chirdList = menuIdToChirdMap.get(menu.getParentid());
            } else {
                chirdList = new ArrayList<>();
                menuIdToChirdMap.put(menu.getParentid(), chirdList);
            }
            chirdList.add(menu.getId());
        }
        // 2.3 将未全部选中的一级目录移除
        for (Integer authMenuId : authMenuIds) {
            if (menuIdToChirdMap.containsKey(authMenuId)) { //若为一级目录
                List<Integer> scdMenuIds = menuIdToChirdMap.get(authMenuId);
                if (authMenuIds.containsAll(scdMenuIds)) { //对应二级目录全包含的情况下添加到结果集
                    resultList.add(authMenuId);
                }
            } else { //二级目录直接添加到结果集
                resultList.add(authMenuId);
            }
        }
        return resultList;
    }

}
