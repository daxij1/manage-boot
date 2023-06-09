package com.daxij1.manageboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daxij1.manageboot.pojo.entity.Auth;
import com.daxij1.manageboot.pojo.param.AuthUpdateParam;

import java.util.List;

public interface AuthService extends IService<Auth> {

    void update(AuthUpdateParam param);

    List<Auth> getAuthByRoleid(Integer roleid);

    List<Integer> roleOwnMenuIdList(Integer roleid);

}
