package com.daxij1.manageboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daxij1.manageboot.framework.util.AesUtil;
import com.daxij1.manageboot.mapper.UserMapper;
import com.daxij1.manageboot.pojo.entity.User;
import com.daxij1.manageboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ：daxij1
 * @date ：Created in 2023/5/25 13:48
 * @description：UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper mapper;
    
    @Override
    public User loginValid(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", AesUtil.encode(password));
        return mapper.selectOne(queryWrapper);
    }
    
}
