package com.daxij1.manageboot.interceptor;

import com.daxij1.manageboot.pojo.dto.SessionUserDTO;
import com.daxij1.manageboot.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoteIpStatisticsInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger("access");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        String ipAddr = IPUtils.getIpAddr(request);
        SessionUserDTO userDTO = (SessionUserDTO) request.getSession().getAttribute("user");
        if (userDTO != null) {
            LOGGER.info("{},{},{},{}", ipAddr, userDTO.getId(), userDTO.getNickname(), request.getRequestURI());
        } else {
            LOGGER.info("{},{}", ipAddr, request.getRequestURI());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, @Nullable Exception ex) {
    }

}