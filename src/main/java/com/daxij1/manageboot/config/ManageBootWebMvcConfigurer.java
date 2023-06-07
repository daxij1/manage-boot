package com.daxij1.manageboot.config;

import com.daxij1.manageboot.interceptor.AuthInterceptor;
import com.daxij1.manageboot.interceptor.LoginInterceptor;
import com.daxij1.manageboot.property.ManagebootSysProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/26 9:55
 * @description：ManageBootWebMvcConfigurer
 */
@Configuration
public class ManageBootWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private ManagebootSysProperty property;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(property.getNoauthUrls());
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**").excludePathPatterns(property.getNoauthUrls());
    }

}

