package com.daxij1.manageboot.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.daxij1.manageboot.framework.annotation.Auth;
import com.daxij1.manageboot.framework.pojo.ResponseVO;
import com.daxij1.manageboot.pojo.param.AuthUpdateParam;
import com.daxij1.manageboot.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
@DS("manageboot")
@Auth(role = "系统管理员")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 查询角色拥有的菜单ids
     * */
    @GetMapping("/roleOwnMenuIdList")
    public ResponseVO<List<Integer>> roleOwnMenuIdList(Integer roleid){
        if ((roleid == null)) {
            return ResponseVO.paramFail("角色id不能为空");
        }
        return ResponseVO.success(authService.roleOwnMenuIdList(roleid));
    }

    @PostMapping("/update")
    public ResponseVO<Object> update(@Valid @RequestBody AuthUpdateParam param) {
        authService.update(param);
        return ResponseVO.success();
    }

}
