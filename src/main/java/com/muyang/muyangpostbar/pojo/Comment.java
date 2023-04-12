package com.muyang.muyangpostbar.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:25:26
 */
@Data
public class Comment {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer belongUserId;
    Integer belongBarId;
    Integer belongInvitation;
    String content;
}
