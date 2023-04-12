package com.muyang.muyangpostbar.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.pojo.Invitation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-28 15:02:19
 */
@Mapper
public interface InvitationMapper extends BaseMapper<Invitation> {
    @Insert("insert into invitation (topic,content,belong_user_email,belong_bar_name,create_time,like_count,comment_count) values ('${topic}','${content}','${belongUserEmail}','${belongBarName}',now(),0,0)")
    void saveInvitation(String topic, String content, String belongUserEmail, String belongBarName);
}
