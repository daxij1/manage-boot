package com.daxij1.manageboot.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/25 17:19
 * @description：JsonUtil
 */
public class JsonUtil {
    
    public static String toJsonString(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
    
}
