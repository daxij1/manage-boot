package com.daxij1.manageboot.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.daxij1.manageboot.interceptor.AuthInterceptor;
import com.daxij1.manageboot.interceptor.LoginInterceptor;
import com.daxij1.manageboot.interceptor.RemoteIpStatisticsInterceptor;
import com.daxij1.manageboot.property.ManagebootSysProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

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
        registry.addInterceptor(new RemoteIpStatisticsInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet,"/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", property.getDruidStatUserName());
        initParams.put("loginPassword", property.getDruidStatPassword());
        initParams.put("allow", property.getDruidStatAllow());//允许访问的ip，不写则不限制
        initParams.put("deny", "");
        registrationBean.setInitParameters(initParams);
        return registrationBean;
    }

}

