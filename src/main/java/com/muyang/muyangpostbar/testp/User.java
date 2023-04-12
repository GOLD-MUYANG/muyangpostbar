package com.muyang.muyangpostbar.testp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.time.LocalDateTime;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:05:40
 */
public class User {
    /**
     * TableId是主键注解，type类型为主键类型，auto为自动递增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 如果字段名称和实体名称不一致的话，TableField可以进行字段值的映射，
     */
    @TableField("name")
    private String userName;

    @TableField("age")
    private String age;

    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * TableLogic用于逻辑删除，使用时，在查找的时候会默认过滤掉已删除的数据
     * 而不用显式地声明is_delete
     * 注意：这里只对自动注入地SQL生效（即自动生成的sql），其他的sql不可用.
     */
    @TableLogic(value = "0",delval = "1")
    @TableField("is_delete")
    private Integer isDelete;
}
