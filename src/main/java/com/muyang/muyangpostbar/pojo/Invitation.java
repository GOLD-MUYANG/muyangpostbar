package com.muyang.muyangpostbar.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-28 14:44:31
 */
@Data
public class Invitation {
    Integer id;
    String topic;
    String content;
    String belongBarName;
    String belongUserEmail;
    Date createTime;
    Date updateTime;
    Long likeCount;
    Long commentCount;
}
