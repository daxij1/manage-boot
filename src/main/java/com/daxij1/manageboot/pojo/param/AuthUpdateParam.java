package com.daxij1.manageboot.pojo.param;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AuthUpdateParam {

    @NotNull(message = "角色id不能为空")
    @Min(message = "角色id参数错误", value = 1)
    private Integer roleid;

    @NotNull(message = "菜单参数不能为null")
    private List<Integer> menuIds;

}
