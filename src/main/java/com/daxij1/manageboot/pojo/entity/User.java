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
@TableName("T_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 头像
     */
    @TableField("AVATOR")
    private String avator;

    /**
     * 昵称
     */
    @TableField("NICKNAME")
    private String nickname;

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
