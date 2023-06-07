package com.daxij1.manageboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daxij1.manageboot.mapper.RoleUserbindingMapper;
import com.daxij1.manageboot.pojo.entity.RoleUserbinding;
import com.daxij1.manageboot.service.RoleUserbindingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/7 15:23
 * @description：RoleUserbindingServiceImpl
 */
@Service
public class RoleUserbindingServiceImpl extends ServiceImpl<RoleUserbindingMapper, RoleUserbinding> implements RoleUserbindingService {
    
    @Override
    public List<String> findRoleNamesByUserId(Integer userId){
        return getBaseMapper().findRoleNamesByUserId(userId);
    }
    
}
