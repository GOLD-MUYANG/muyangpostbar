package com.muyang.muyangpostbar.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-12 15:16:26
 */
@Data
@Accessors(chain = true)
@TableName("user_info")
public class User{
    @TableId(value="id",type= IdType.AUTO)
    Integer id;
    String email;
    String password;
    Integer state;
    String realName;
    String nickName;
    String head;
    Date createTime;
    Date updateTime;
}
