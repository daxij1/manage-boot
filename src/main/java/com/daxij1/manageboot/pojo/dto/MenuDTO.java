package com.daxij1.manageboot.pojo.dto;

import com.daxij1.manageboot.pojo.entity.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：daxij1
 * @date ：Created in 2023/6/1 9:12
 * @description：MenuDTO
 */
@Data
public class MenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer level;

    private Integer type;

    private Integer parentid;

    private String icon;

    private String descr;

    private List<Menu> menuList;

}