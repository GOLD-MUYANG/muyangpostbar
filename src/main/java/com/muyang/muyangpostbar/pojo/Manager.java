package com.muyang.muyangpostbar.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-13 21:31:03
 */
@Data
public class Manager {
    @TableId(value="id",type= IdType.AUTO)
    Integer id;
    String email;
    String name;
    String password;
    String state;
    Date createTime;
    Date updateTime;
    Integer roleId;
    //manager表里本来是没有的，添加上方便查询
    @TableField(exist = false)
    List<Role> roles;
}
