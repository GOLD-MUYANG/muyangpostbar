package com.muyang.muyangpostbar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyang.muyangpostbar.pojo.Manager;
import com.muyang.muyangpostbar.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-13 21:32:42
 */
@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
    @Select("Select action_name,url from manager " +
            "left join role on manager.role_id = role.role_id " +
            "left join role_action on role.action_id=role_action.action_id " +
            "where manager.role_id=${roleId}")
    List<Role> getManagerRoles(Integer roleId);
}
