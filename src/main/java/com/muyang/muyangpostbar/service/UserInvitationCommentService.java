package com.muyang.muyangpostbar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muyang.muyangpostbar.pojo.UserInvitationComment;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-04-11 12:57:59
 */
public interface UserInvitationCommentService extends IService<UserInvitationComment> {
    List<UserInvitationComment> getComment(Integer invitationId);
}
