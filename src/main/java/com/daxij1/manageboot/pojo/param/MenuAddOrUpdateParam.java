package com.daxij1.manageboot.pojo.param;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/8 13:57
 * @description：MenuAddOrUpdateParam
 */
@Data
public class MenuAddOrUpdateParam {

    private Integer id;

    @NotEmpty(message = "菜单名称不能为空")
    private String name;

    @NotNull(message = "菜单层级不能为空")
    @Min(message = "菜单层级参数错误", value = 0)
    private Integer level;
    
    private Integer parentid;
    
    private String icon;
    
    private String path;
    
    private String descr;
    
    private Integer sort;
    
}
