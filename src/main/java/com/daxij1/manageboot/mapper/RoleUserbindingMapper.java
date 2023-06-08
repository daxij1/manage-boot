package com.daxij1.manageboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daxij1.manageboot.pojo.entity.RoleUserbinding;
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
public interface RoleUserbindingMapper extends BaseMapper<RoleUserbinding> {

    List<String> findRoleNamesByUserId(@Param("userid") Integer userid);
    
    List<Integer> findRoleIdsByUserId(@Param("userid") Integer userid);

}
