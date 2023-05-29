package com.daxij1.manageboot.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/24 17:09
 * @description：LoginForm
 */
@Data
public class LoginFormParam {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;
    
}
