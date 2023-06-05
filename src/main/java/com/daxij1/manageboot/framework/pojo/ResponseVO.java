package com.daxij1.manageboot.framework.pojo;

import com.daxij1.manageboot.framework.util.JsonUtil;
import lombok.Data;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    public static final Integer AUTH_FAILD = 401;
    
    public static final Integer PARAM_FAILD = 403;

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
        return buildResponse(FAILD, msg, null);
    }

    public static <T> ResponseVO<T> authFail() {
        return buildResponse(AUTH_FAILD, "未登录或登录失效", null);
    }
    
    public static <T> ResponseVO<T> paramFail(String msg) {
        return buildResponse(PARAM_FAILD, msg, null);
    }

    public static <T> ResponseVO<T> success(int code, String msg, T data) {
        return buildResponse(code, msg, data);
    }

    public static <T> ResponseVO<T> buildResponse(int code, String msg, T data) {
        return new ResponseVO<>(code, msg, data);
    }

    public static void write(HttpServletResponse resp, ResponseVO<Object> result) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(JsonUtil.toJsonString(result));
        writer.close();
    }

}

