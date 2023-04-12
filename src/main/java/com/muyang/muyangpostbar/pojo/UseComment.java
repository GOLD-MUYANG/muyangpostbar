package com.muyang.muyangpostbar.pojo;

import lombok.Data;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:26:54
 */

/**
 * 这个是用来多表联查数据的comment，把id显示为正常的需要的值
 **/
@Data
public class UseComment {
    Integer id;
    String belongUserEmail;
    String belongBarName;
    Integer belongInvitation;
    String content;
}
