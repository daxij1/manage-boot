package com.daxij1.manageboot.pojo.param;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/8 20:03
 * @description：UpdateUserRoleParam
 */
@Data
public class UserRoleUpdateParam {

    @NotNull(message = "用户id不能为空")
    @Min(message = "用户id参数错误", value = 1)
    private Integer userid;

    @NotEmpty(message = "用户角色不能为空")
    private List<Integer> roleIds;
    
}
