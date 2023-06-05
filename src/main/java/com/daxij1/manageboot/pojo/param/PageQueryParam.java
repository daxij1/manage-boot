package com.daxij1.manageboot.pojo.param;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/2 16:51
 * @description：CommonQueryParam
 */
@Data
public class PageQueryParam {

    @NotNull(message = "pageno不能为空")
    @Min(message = "pageno参数错误", value = 1)
    private int pageno;

    @NotNull(message = "pagesize不能为空")
    @Min(message = "pagesieze参数错误", value = 1)
    private int pagesize;
    
}
