package com.daxij1.manageboot.pojo.param;

import lombok.Data;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/8 10:33
 * @description：MenuQueryParam
 */
@Data
public class MenuQueryParam extends PageQueryParam {
    
    private Integer level;
    
    private String name;
    
}
