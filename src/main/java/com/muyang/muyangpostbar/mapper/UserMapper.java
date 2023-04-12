package com.muyang.muyangpostbar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyang.muyangpostbar.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-12 19:48:50
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select count(*) from user_info where date(create_time) between date_sub(curdate(),interval 1 day) and now()")
    int getUserD1();
    @Select("select count(*) from user_info where date(create_time) between date_sub(curdate(),interval 2 day) and date_sub(curdate(),interval 1 day)")
    int getUserD2();
    @Select("select count(*) from user_info where date(create_time) between date_sub(curdate(),interval 3 day) and date_sub(curdate(),interval 2 day)")
    int getUserD3();
    @Select("select count(*) from user_info where date(create_time) between date_sub(curdate(),interval 4 day) and date_sub(curdate(),interval 3 day)")
    int getUserD4();
    @Select("select count(*) from user_info where date(create_time) between date_sub(curdate(),interval 5 day) and date_sub(curdate(),interval 4 day)")
    int getUserD5();
    @Select("select count(*) from user_info where date(create_time) between date_sub(curdate(),interval 6 day) and date_sub(curdate(),interval 5 day)")
    int getUserD6();
    @Select("select count(*) from user_info where date(create_time) between date_sub(curdate(),interval 7 day) and date_sub(curdate(),interval 6 day)")
    int getUserD7();
}
