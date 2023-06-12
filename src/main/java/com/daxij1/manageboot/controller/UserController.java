package com.daxij1.manageboot.controller;

import cn.hutool.core.io.file.FileNameUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daxij1.manageboot.framework.annotation.Auth;
import com.daxij1.manageboot.framework.exeception.ServiceException;
import com.daxij1.manageboot.framework.pojo.ResponseVO;
import com.daxij1.manageboot.pojo.dto.SessionUserDTO;
import com.daxij1.manageboot.pojo.entity.User;
import com.daxij1.manageboot.pojo.param.*;
import com.daxij1.manageboot.service.RoleUserbindingService;
import com.daxij1.manageboot.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
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

    @Value("${local-file.address}")
    private String fileAddress;

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleUserbindingService roleUserbindingService;
    
    @PostMapping("/login")
    public ResponseVO<Object> login(@Valid @RequestBody LoginFormParam loginFormParam, HttpServletRequest request) throws ServiceException {
        String username = loginFormParam.getUsername();
        String password = loginFormParam.getPassword();
        User user = userService.loginValid(username, password);
        if (user == null) {
            throw new ServiceException("用户名或密码错误");
        }
        List<String> roles = roleUserbindingService.findRoleNamesByUserId(user.getId());
        SessionUserDTO sessionUser = new SessionUserDTO();
        BeanUtils.copyProperties(user, sessionUser);
        sessionUser.setRoles(roles);
        String avator = sessionUser.getAvator();
        if (!StringUtils.startsWith(avator, "http") && !StringUtils.startsWith(avator, ".")) {
            sessionUser.setAvator(fileAddress + avator);
        }
        request.getSession().setAttribute("user", sessionUser);
        return ResponseVO.success();
    }

    @GetMapping("/logout")
    public ResponseVO<Object> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return ResponseVO.success();
    }
    
    @PostMapping("/list")
    @Auth(role = "系统管理员")
    public ResponseVO<PageInfo<User>> list(@Valid @RequestBody UserQueryParam param,HttpSession session){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 0);
        if (StringUtils.isNotEmpty(param.getUsername())) {
            queryWrapper.eq("username", param.getUsername());
        }
        SessionUserDTO sessionUser = (SessionUserDTO) session.getAttribute("user");
        if (!sessionUser.getRoles().contains("超级管理员")) { //只有超级管理员才能管理超级管理员用户
            queryWrapper.notIn("id", 0);
        }
        PageHelper.startPage(param.getPageno(), param.getPagesize());
        List<User> list = userService.list(queryWrapper);
        for (User user : list) {
            String avator = user.getAvator();
            if (!StringUtils.startsWith(avator, "http") && !StringUtils.startsWith(avator, ".")) {
                user.setAvator(fileAddress + avator);
            }
        }
        return ResponseVO.success(new PageInfo<>(list));
    }

    @PostMapping("/addOrUpdate")
    @Auth(role = "系统管理员")
    public ResponseVO<Object> addOrUpdate(@Valid @RequestBody UserAddOrUpdateParam param){
        Integer id = param.getId();
        User user = new User();
        BeanUtils.copyProperties(param, user);
        if (id != null) { //更新
            user.setLastmodifiedtime(new Date());
            userService.updateById(user);
        } else { //新增
            user.setAvator(User.DEF_AVATOR);
            userService.save(user);
        }
        return ResponseVO.success();
    }

    @GetMapping("/delete")
    @Auth(role = "系统管理员")
    public ResponseVO<Object> delete(Integer id){
        if (id == null || id <= 0) {
            return ResponseVO.paramFail("用户id有误");
        }
        User user = new User();
        user.setId(id);
        user.setDel(1);
        userService.updateById(user);
        return ResponseVO.success();
    }
    
    @RequestMapping("/updateRole")
    @Auth(role = "系统管理员")
    public ResponseVO<Object> updateRole(@Valid @RequestBody UserRoleUpdateParam param){
        roleUserbindingService.updateRolesForUser(param.getUserid(), param.getRoleIds());
        return ResponseVO.success();
    }

    @RequestMapping("/updateInfo")
    public ResponseVO<Object> updateInfo(@Valid @RequestBody UserInfoUpdateParam param, HttpSession session){
        SessionUserDTO sessionUser = (SessionUserDTO) session.getAttribute("user");
        userService.updateUserInfo(sessionUser, param);
        return ResponseVO.success();
    }

    @RequestMapping("/updatePassword")
    public ResponseVO<Object> updatePassword(@Valid @RequestBody PasswordUpdateParam param, HttpSession session) throws ServiceException {
        if (!StringUtils.equals(param.getNnew(), param.getConfirmNew())) {
            return ResponseVO.paramFail("两次密码输入不一致");
        }
        SessionUserDTO sessionUser = (SessionUserDTO) session.getAttribute("user");
        userService.updatePassword(sessionUser.getId(), param.getOld(), param.getNnew());
        return ResponseVO.success();
    }

}
