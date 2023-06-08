package com.daxij1.manageboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daxij1.manageboot.pojo.entity.RoleUserbinding;

import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/7 15:23
 * @description：RoleUserbindingService
 */
public interface RoleUserbindingService extends IService<RoleUserbinding> {

    List<String> findRoleNamesByUserId(Integer userId);
    
    void updateRolesForUser(Integer userid, List<Integer> roleIds);
    
}
