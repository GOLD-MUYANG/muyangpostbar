package com.muyang.muyangpostbar.pojo;

import lombok.Data;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-04-11 12:55:37
 */
@Data
public class UserInvitationComment {
    Integer id;
    Integer userId;
    String userNickName;
    String userHead;
    String content;
    Integer belongInvitation;
}
