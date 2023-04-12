package com.muyang.muyangpostbar.testp;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:06:16
 */
@Data
@TableName("book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1710243792291177806L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 书籍名称
     */
    @TableField("name")
    private String name;

    /**
     * 归属用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 书记作者
     */
    @TableField("author")
    private String author;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    @TableLogic(value = "0", delval = "1")
    private Boolean isDelete;
}
