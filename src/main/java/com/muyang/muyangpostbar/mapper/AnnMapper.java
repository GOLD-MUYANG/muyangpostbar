package com.muyang.muyangpostbar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyang.muyangpostbar.pojo.Ann;
import com.muyang.muyangpostbar.utils.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-20 20:06:53
 */
@Mapper
public interface AnnMapper extends BaseMapper<Ann> {
    @Select("select topic,content,create_time from ann ORDER BY create_time desc limit 3")
    List<Ann> getAnn();
}
