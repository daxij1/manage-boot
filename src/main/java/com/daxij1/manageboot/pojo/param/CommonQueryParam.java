package com.daxij1.manageboot.pojo.param;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/2 16:51
 * @description：CommonQueryParam
 */
@Data
public class CommonQueryParam {

    @NotNull(message = "pageno不能为空")
    @Range(message = "pageno需大于等于1", min = 1)
    private int pageno;

    @NotNull(message = "pagesize不能为空")
    private int pagesize;
    
}
