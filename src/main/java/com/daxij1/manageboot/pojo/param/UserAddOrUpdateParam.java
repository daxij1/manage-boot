package com.daxij1.manageboot.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/5 11:10
 * @description：UserUpdateParam
 */
@Data
public class UserAddOrUpdateParam {
    
    private Integer id;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    private String nickname;
    
    
}
