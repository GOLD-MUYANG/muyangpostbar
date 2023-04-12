package com.muyang.muyangpostbar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muyang.muyangpostbar.pojo.UserInterestBar;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-04-01 21:40:23
 */
public interface UserInterestBarService extends IService<UserInterestBar> {
    void saveUserInterestBar(Integer userId, Integer barId);
}
