package com.muyang.muyangpostbar.testp;

import lombok.Data;

import java.util.Date;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:10:15
 */
@Data
public class UserBookVO {
    private int id;
    private String name;
    private Integer userId;
    private String userName;
    private String author;
    private Date createTime;
}
