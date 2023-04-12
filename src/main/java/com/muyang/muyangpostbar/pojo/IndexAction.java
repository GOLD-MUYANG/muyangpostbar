package com.muyang.muyangpostbar.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-22 21:35:24
 */
@Data
@TableName("index_action")
public class IndexAction {
    Integer id;
    String name;
    String url;
}
