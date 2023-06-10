package com.daxij1.manageboot.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PasswordUpdateParam {

    @NotEmpty(message = "旧密码不能为空")
    private String old;

    @NotEmpty(message = "新密码不能为空")
    private String nnew;

    @NotEmpty(message = "确认新密码不能为空")
    private String confirmNew;

}
