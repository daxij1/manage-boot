package com.daxij1.manageboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daxij1.manageboot.pojo.dto.MenuDTO;
import com.daxij1.manageboot.pojo.entity.Menu;
import com.daxij1.manageboot.pojo.vo.MenuTreeVO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/31 15:34
 * @description：MenuService
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取用户的菜单列表，left返回菜单树，right返回所有二级菜单
     */
    Pair<List<MenuDTO>, List<Menu>> findUserMenuList(long userid);
    
    /**
     * 构建目录树结构
     * */
    List<MenuTreeVO> treeMenuList();

}
