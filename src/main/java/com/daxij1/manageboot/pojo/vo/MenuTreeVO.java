package com.daxij1.manageboot.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/9 17:30
 * @description：MenuTreeVO
 */
@Data
public class MenuTreeVO {
    
    private Integer id;
    
    private String name;
    
    private List<MenuTreeVO> children;
    
}
