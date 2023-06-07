package com.daxij1.manageboot.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/7 15:40
 * @description：SessionUserDTO
 */
@Data
public class SessionUserDTO {

    private Integer id;
    
    private String username;
    
    private String nickname;

    private String avator;
    
    private List<String> roles;
    
}
