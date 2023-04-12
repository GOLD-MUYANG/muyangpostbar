package com.muyang.muyangpostbar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyang.muyangpostbar.mapper.ManagerMapper;
import com.muyang.muyangpostbar.pojo.Manager;
import com.muyang.muyangpostbar.pojo.Role;
import com.muyang.muyangpostbar.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-14 14:01:20
 */
@Service
public class ManagerSeriveImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    @Override
    public List<Role> getManagerRoles(Integer roleId) {
        return managerMapper.getManagerRoles(roleId);
    }
}
