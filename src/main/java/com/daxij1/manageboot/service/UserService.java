package com.daxij1.manageboot.service;

import com.daxij1.manageboot.pojo.entity.User;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/25 10:16
 * @description：UserService
 */
public interface UserService {
    
    User loginValid(String username,String password);
    
}
