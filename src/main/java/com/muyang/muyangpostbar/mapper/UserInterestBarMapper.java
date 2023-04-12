package com.muyang.muyangpostbar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyang.muyangpostbar.pojo.UserInterestBar;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-04-01 21:39:33
 */
@Mapper
public interface UserInterestBarMapper extends BaseMapper<UserInterestBar> {
    @Insert("insert into user_interest_bar(user_id,bar_id) values (${userId},${barId})")
    void saveUserInterestBar(Integer userId, Integer barId);
}
