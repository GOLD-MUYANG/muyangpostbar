package com.muyang.muyangpostbar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyang.muyangpostbar.pojo.UserInvitationComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-04-11 12:57:33
 */
@Mapper
public interface UserInvitationCommentMapper extends BaseMapper<UserInvitationComment> {
    @Select("select comment.id as id, comment.belong_invitation as belong_invitation, user_info.id as user_id, real_name as user_nick_name,head as user_head,content from comment " +
            "left join user_info on comment.belong_user_id = user_info.id " +
            "where belong_invitation = ${invitationId}")
    List<UserInvitationComment> getComment(Integer invitationId);
}
