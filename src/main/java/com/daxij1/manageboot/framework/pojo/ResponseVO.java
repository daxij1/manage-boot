package com.daxij1.manageboot.framework.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/24 16:16
 * @description：Response
 */
@Data
@ToString
public class ResponseVO<T> implements Serializable {

    public static final Integer SUCCESS = 200;
    
    public static final Integer AUTH_FAILD = 403;
    
    public static final Integer FAILD = 500;

    private int code;

    private String msg;

    private T data;

    public ResponseVO() {
    }

    public ResponseVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseVO(int code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseVO<T> success() {
        return buildResponse(SUCCESS, "success", null);
    }

    public static <T> ResponseVO<T> success(String msg) {
        return buildResponse(SUCCESS, msg, null);
    }

    public static <T> ResponseVO<T> success(T data) {
        return buildResponse(SUCCESS, "success", data);
    }

    public static <T> ResponseVO<T> success(int code, String msg) {
        return buildResponse(code, msg, null);
    }

    public static <T> ResponseVO<T> error() {
        return buildResponse(FAILD, "服务器开小差了~", null);
    }

    public static <T> ResponseVO<T> error(int code, String msg) {
        return new ResponseVO<T>(code, msg);
    }

    public static <T> ResponseVO<T> error(String msg) {
        return buildResponse(FAILD, msg,  null);
    }
    
    public static <T> ResponseVO<T> authFail() {
        return buildResponse(AUTH_FAILD, "未登录或登录失效",  null);
    }

    public static <T> ResponseVO<T> success(int code, String msg, T data) {
        return buildResponse(code, msg, data);
    }

    public static <T> ResponseVO<T> buildResponse(int code, String msg, T data) {
        return new ResponseVO<>(code, msg, data);
    }
    
}

