package com.daxij1.manageboot.framework.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：yanghong
 * @date ：Created in 2022/11/21 13:53
 * @description：PageHelpConfig
 */
@Configuration
public class PageHelpConfig {

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
    
}