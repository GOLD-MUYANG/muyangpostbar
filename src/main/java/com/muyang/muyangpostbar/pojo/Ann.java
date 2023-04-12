package com.muyang.muyangpostbar.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-20 20:04:53
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("ann")
public class Ann {
    @TableId(type = IdType.AUTO)
    String id;
    String topic;
    String content;
    Date createTime;
    Date updateTime;
    Integer createManagerId;
}
