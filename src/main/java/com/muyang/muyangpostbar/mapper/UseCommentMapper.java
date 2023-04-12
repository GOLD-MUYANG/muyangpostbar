package com.muyang.muyangpostbar.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.pojo.UseComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:35:55
 */
@Mapper
public interface UseCommentMapper extends BaseMapper<UseComment> {
    @Select("select " +
            "comm.id as id,user.email as belong_user_email," +
            "bar.bar_name as belong_bar_name, comm.content as content," +
            "comm.belong_invitation as belong_invitation" +
            " from comment comm " +
            "left join user_info user on " +
            "user.id=comm.belong_user_id " +
            "left join bar on bar.id = comm.belong_bar_id ${ew.customSqlSegment} ")
    Page<UseComment> getCommentBySearchPage(Page<UseComment> page, @Param(Constants.WRAPPER) QueryWrapper<UseComment> queryWrapper);
}
