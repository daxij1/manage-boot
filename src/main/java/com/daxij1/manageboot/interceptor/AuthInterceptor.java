package com.daxij1.manageboot.interceptor;

import com.daxij1.manageboot.framework.annotation.Auth;
import com.daxij1.manageboot.framework.pojo.ResponseVO;
import com.daxij1.manageboot.pojo.dto.SessionUserDTO;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/7 14:47
 * @description：AuthInterceptor
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            SessionUserDTO sessionUser = (SessionUserDTO) request.getSession().getAttribute("user");
            List<String> roles = sessionUser.getRoles();
            // 超级管理员不受@Auth限制，具备所有权限
            if (roles.contains("超级管理员")) { 
                return true;
            }
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 优先读取方法上@Auth
            Auth authAnnotation = handlerMethod.getMethodAnnotation(Auth.class);
            if (authAnnotation == null) {
                // 方法上没有，读取类上的@Auth
                authAnnotation = ((HandlerMethod) handler).getBeanType().getAnnotation(Auth.class);
            }
            // 权限验证
            if (authAnnotation != null) {
                String[] needRoles = authAnnotation.role();
                for (String needRole : needRoles) {
                    if (roles.contains(needRole)) {
                        return true;
                    }
                }
                ResponseVO.write(response, ResponseVO.authRoleFail());
                return false;
            }
        }
        return true;
    }
    
}
