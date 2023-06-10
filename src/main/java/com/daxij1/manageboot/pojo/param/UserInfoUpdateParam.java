package com.daxij1.manageboot.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserInfoUpdateParam {

    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @NotEmpty(message = "头像不能为空")
    private String avator;

}
