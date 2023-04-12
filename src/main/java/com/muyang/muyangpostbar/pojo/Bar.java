package com.muyang.muyangpostbar.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-27 15:15:25
 */
@Data
public class Bar {
    @TableId(type = IdType.AUTO)
    Integer id;
    String barName;
    String barHost;
    Long userCountAll;
    String barHead;
    String barUrl;
    String barIntroduction;
}
