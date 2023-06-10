package com.daxij1.manageboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daxij1.manageboot.framework.exeception.ServiceException;
import com.daxij1.manageboot.framework.util.AesUtil;
import com.daxij1.manageboot.mapper.UserMapper;
import com.daxij1.manageboot.pojo.dto.SessionUserDTO;
import com.daxij1.manageboot.pojo.entity.User;
import com.daxij1.manageboot.pojo.param.UserInfoUpdateParam;
import com.daxij1.manageboot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author ：daxij1
 * @date ：Created in 2023/5/25 13:48
 * @description：UserServiceImpl
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User loginValid(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", AesUtil.encode(password));
        queryWrapper.eq("del", 0);
        return getBaseMapper().selectOne(queryWrapper);
    }

    @Override
    public void updateUserInfo(SessionUserDTO sessionUserDTO, UserInfoUpdateParam param) {
        User user = new User();
        user.setId(sessionUserDTO.getId());
        user.setAvator(param.getAvator());
        user.setNickname(param.getNickname());
        user.setLastmodifiedtime(new Date());
        getBaseMapper().updateById(user);
        sessionUserDTO.setAvator(param.getAvator());
        sessionUserDTO.setNickname(param.getNickname());
    }

    @Override
    public void updatePassword(int userid, String old, String nnew) throws ServiceException {
        User user = getBaseMapper().selectById(userid);
        if (!StringUtils.equals(AesUtil.encode(old), user.getPassword())) {
            throw new ServiceException("旧密码验证失败，请输入正确的密码");
        }
        user.setPassword(AesUtil.encode(nnew));
        getBaseMapper().updateById(user);
    }

}
