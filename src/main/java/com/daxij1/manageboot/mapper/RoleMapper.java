package com.daxij1.manageboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daxij1.manageboot.pojo.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author daxij1
 * @since 2023-06-02
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findUserOwnRoleList(@Param("userid") Integer userid);

}
