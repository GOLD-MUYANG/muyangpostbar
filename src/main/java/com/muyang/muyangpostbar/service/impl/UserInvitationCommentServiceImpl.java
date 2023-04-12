package com.muyang.muyangpostbar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyang.muyangpostbar.mapper.UserInvitationCommentMapper;
import com.muyang.muyangpostbar.pojo.UserInvitationComment;
import com.muyang.muyangpostbar.service.UserInvitationCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-04-11 12:58:37
 */
@Service
public class UserInvitationCommentServiceImpl extends ServiceImpl<UserInvitationCommentMapper, UserInvitationComment> implements UserInvitationCommentService {
    @Autowired
    UserInvitationCommentMapper userInvitationCommentMapper;

    @Override
    public List<UserInvitationComment> getComment(Integer invitationId) {
        List<UserInvitationComment> lists = userInvitationCommentMapper.getComment(invitationId);
        return lists;
    }
}
