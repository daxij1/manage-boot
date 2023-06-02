package com.daxij1.manageboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daxij1.manageboot.pojo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author daxij1
 * @since 2023-05-31
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * @param userid 用户id
     * @param level  菜单级别
     */
    List<Menu> selectListByUserid(@Param("userid") long userid, @Param("level") int level);

}
