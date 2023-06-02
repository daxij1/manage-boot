package com.daxij1.manageboot.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author daxij1
 * @since 2023-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_MENU")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 菜单级别 1、2
     */
    @TableField("LEVEL")
    private Integer level;

    /**
     * 菜单类型 1-目录型 2-菜单项
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 父级菜单id
     */
    @TableField("PARENTID")
    private Integer parentid;

    /**
     * 图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * 页面路径
     */
    @TableField("PATH")
    private String path;

    /**
     * 描述
     */
    @TableField("DESCR")
    private String descr;

    /**
     * 启用标志，0停用1启用
     */
    @TableField("ACTIVE")
    private Integer active;

    /**
     * 排序字段
     */
    @TableField("SORT")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField("CREATETIME")
    private Date createtime;

    /**
     * 最近更新时间
     */
    @TableField("LASTMODIFIEDTIME")
    private Date lastmodifiedtime;


}
