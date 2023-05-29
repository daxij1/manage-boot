package com.daxij1.manageboot.framework.exeception;

import com.daxij1.manageboot.framework.pojo.ResponseVO;
import lombok.Data;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/24 17:23
 * @description：ServiceException
 */
@Data
public class ServiceException extends Exception {

    private Integer errorCode;

    private String msg;

    public ServiceException(int errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public ServiceException(String msg) {
        this(ResponseVO.FAILD, msg);
    }
    
}
