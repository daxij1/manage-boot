package com.daxij1.manageboot.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.daxij1.manageboot.framework.exeception.ServiceException;
import com.daxij1.manageboot.framework.pojo.ResponseVO;
import com.daxij1.manageboot.pojo.entity.User;
import com.daxij1.manageboot.pojo.param.LoginFormParam;
import com.daxij1.manageboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/24 16:14
 * @description：SsoController
 */
@RestController
@RequestMapping("/user")
@DS("manageboot")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/login")
    public ResponseVO<Object> login(@Valid @RequestBody LoginFormParam loginFormParam, HttpServletRequest request) throws ServiceException {
        String username = loginFormParam.getUsername();
        String password = loginFormParam.getPassword();
        User user = userService.loginValid(username, password);
        if (user == null) {
            throw new ServiceException("用户名或密码错误");
        }
        request.getSession().setAttribute("user", user);
        return ResponseVO.success();
    }

    @GetMapping("/logout")
    public ResponseVO<Object> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return ResponseVO.success();
    }

    @GetMapping("/userinfo")
    public ResponseVO<Object> info(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return ResponseVO.success(user);
    }
    
}
