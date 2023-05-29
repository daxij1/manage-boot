package com.daxij1.manageboot.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/25 16:42
 * @description：@Value无法读取数组、Map等，使用@ConfigurationProperties来读取
 */
@Configuration
@ConfigurationProperties(prefix = "manageboot")
@Data
public class ManagebootSysProperty {
    
    private List<String> noauthUrls;
    
}
