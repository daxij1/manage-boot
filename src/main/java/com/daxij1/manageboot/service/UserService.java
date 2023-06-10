package com.daxij1.manageboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daxij1.manageboot.framework.exeception.ServiceException;
import com.daxij1.manageboot.pojo.dto.SessionUserDTO;
import com.daxij1.manageboot.pojo.entity.User;
import com.daxij1.manageboot.pojo.param.UserInfoUpdateParam;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/25 10:16
 * @description：UserService
 */
public interface UserService extends IService<User> {

    User loginValid(String username, String password);

    void updateUserInfo(SessionUserDTO sessionUserDTO, UserInfoUpdateParam param);

    void updatePassword(int userid, String old,String nnew) throws ServiceException;

}
