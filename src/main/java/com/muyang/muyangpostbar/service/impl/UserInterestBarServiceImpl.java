package com.muyang.muyangpostbar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyang.muyangpostbar.mapper.UserInterestBarMapper;
import com.muyang.muyangpostbar.pojo.UserInterestBar;
import com.muyang.muyangpostbar.service.UserInterestBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-04-01 21:40:50
 */
@Service
public class UserInterestBarServiceImpl extends ServiceImpl<UserInterestBarMapper, UserInterestBar> implements UserInterestBarService {

    @Autowired
    UserInterestBarMapper userInterestBarMapper;

    @Override
    public void saveUserInterestBar(Integer userId, Integer barId) {
        userInterestBarMapper.saveUserInterestBar(userId,barId);
    }
}
