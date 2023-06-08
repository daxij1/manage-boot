package com.daxij1.manageboot.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/8 16:03
 * @description：RoleAddOrUpdateParam
 */
@Data
public class RoleAddOrUpdateParam {

    private Integer id;

    @NotEmpty(message = "角色名称不能为空")
    private String name;
    
    private String descr;
    
}
