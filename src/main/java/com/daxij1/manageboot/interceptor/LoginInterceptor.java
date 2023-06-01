package com.daxij1.manageboot.interceptor;

import com.daxij1.manageboot.framework.pojo.ResponseVO;
import com.daxij1.manageboot.pojo.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/25 15:35
 * @description：UserInterceptor
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            if ("/".equals(request.getRequestURI()) || request.getRequestURI().endsWith(".html")) {//首页
                response.sendRedirect("/login.html");
                return false;
            }
            ResponseVO.write(response, ResponseVO.authFail());
            return false;
        }
        return true;
    }
    
}
