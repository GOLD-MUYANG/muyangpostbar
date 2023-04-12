package com.muyang.muyangpostbar.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.pojo.Bar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-27 15:17:30
 */

@Mapper
public interface BarMapper extends BaseMapper<Bar> {
    @Select("select * from bar ORDER BY user_count_all desc limit 3")
    List<Bar> getBar();

    @Select("select enter_time,bar_name,bar_host,user_count_all," +
            "bar_head,bar_url,bar_introduction from user_interest_bar " +
            " LEFT JOIN bar on user_interest_bar.bar_id = bar.id where user_id=${info}  \n" +
            "ORDER BY user_interest_bar.enter_time desc limit 3")
    List<Bar> getUserMostInterestBar(Integer info);

    @Select("select topic,content,email,bar_name,like_count,comment_count" +
            "from invitation left join user_info on belong_user_id = user_info.id" +
            "left join bar on belong_bar_id = bar.id")
    Page<Bar> getBarBySearchPage(QueryWrapper<Bar> wrapper, Page<Bar> page);

    @Select("select count(*) from bar where date(create_time) between date_sub(curdate(),interval 1 day) and now()")
    int getBarD1();

    @Select("select count(*) from bar where date(create_time) between date_sub(curdate(),interval 2 day) and date_sub(curdate(),interval 1 day)")
    int getBarD2();

    @Select("select count(*) from bar where date(create_time) between date_sub(curdate(),interval 3 day) and date_sub(curdate(),interval 2 day)")
    int getBarD3();

    @Select("select count(*) from bar where date(create_time) between date_sub(curdate(),interval 4 day) and date_sub(curdate(),interval 3 day)")
    int getBarD4();

    @Select("select count(*) from bar where date(create_time) between date_sub(curdate(),interval 5 day) and date_sub(curdate(),interval 4 day)")
    int getBarD5();

    @Select("select count(*) from bar where date(create_time) between date_sub(curdate(),interval 6 day) and date_sub(curdate(),interval 5 day)")
    int getBarD6();

    @Select("select count(*) from bar where date(create_time) between date_sub(curdate(),interval 7 day) and date_sub(curdate(),interval 6 day)")
    int getBarD7();


    @Select("select count(*) from user_interest_bar left)")
    int checkIsRegard();

    @Select("select enter_time,bar_name,bar_host,user_count_all," +
            "bar_head,bar_url,bar_introduction from user_interest_bar " +
            " LEFT JOIN bar on user_interest_bar.bar_id = bar.id where user_id=${info}"
    )
    List<Bar> getUserInterestBar(Integer userId);

    @Update("update bar set user_count_all=user_count_all-1")
    void decreaseBarCount();

    @Update("update bar set user_count_all=user_count_all+1")
    void increaseBarCount();
}
