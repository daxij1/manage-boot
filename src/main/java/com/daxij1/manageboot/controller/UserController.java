package com.daxij1.manageboot.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daxij1.manageboot.framework.exeception.ServiceException;
import com.daxij1.manageboot.framework.pojo.ResponseVO;
import com.daxij1.manageboot.framework.util.AesUtil;
import com.daxij1.manageboot.pojo.entity.User;
import com.daxij1.manageboot.pojo.param.CommonQueryParam;
import com.daxij1.manageboot.pojo.param.LoginFormParam;
import com.daxij1.manageboot.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
    
    @PostMapping("/login")
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
    
    @PostMapping("/list")
    public ResponseVO<PageInfo<User>> list(@Valid @RequestBody CommonQueryParam param){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        PageHelper.startPage(param.getPageno(), param.getPagesize());
        List<User> list = userService.list(queryWrapper);
        return ResponseVO.success(new PageInfo<>(list));
    }

    @PostMapping("/add")
    public ResponseVO<Object> add(@Valid @RequestBody User user){
        user.setPassword(AesUtil.encode(user.getPassword()));
        user.setAvator(User.DEF_AVATOR);
        userService.save(user);
        return ResponseVO.success();
    }

}
