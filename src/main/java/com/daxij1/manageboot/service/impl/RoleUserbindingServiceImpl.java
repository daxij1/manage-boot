package com.daxij1.manageboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daxij1.manageboot.mapper.RoleUserbindingMapper;
import com.daxij1.manageboot.pojo.entity.RoleUserbinding;
import com.daxij1.manageboot.service.RoleUserbindingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Override
    @Transactional
    public void updateRolesForUser(Integer userid, List<Integer> roleIds) {
        List<Integer> curRoleIds = getBaseMapper().findRoleIdsByUserId(userid);
        for (Integer roleId : roleIds) {
            if (!curRoleIds.contains(roleId)) {
                RoleUserbinding roleUserbinding = new RoleUserbinding();
                roleUserbinding.setUserid(userid);
                roleUserbinding.setRoleid(roleId);
                getBaseMapper().insert(roleUserbinding);
            }
        }
        for (Integer curRoleId : curRoleIds) {
            if (!roleIds.contains(curRoleId)) {
                QueryWrapper<RoleUserbinding> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("userid", userid);
                queryWrapper.eq("roleid", curRoleId);
                int i = 2/0;
                getBaseMapper().delete(queryWrapper);
            }
        }
        
    }

}
