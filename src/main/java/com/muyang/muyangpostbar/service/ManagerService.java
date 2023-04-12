package com.muyang.muyangpostbar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muyang.muyangpostbar.pojo.Manager;
import com.muyang.muyangpostbar.pojo.Role;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-14 14:00:20
 */
public interface ManagerService extends IService<Manager>{
    List<Role> getManagerRoles(Integer roleId);
}
