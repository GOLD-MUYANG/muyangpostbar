package com.muyang.muyangpostbar.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.testp.Book;
import com.muyang.muyangpostbar.testp.UserBookVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:11:29
 */
@Mapper
@Repository
public interface BookMapper extends BaseMapper<Book> {

    @Select("select k.*,u.name as user_name from book k left join user u on k.user_id = u.id"
            + " ${ew.customSqlSegment}")
    List<UserBookVO> getBookAndUser(Page<UserBookVO> page, @Param(Constants.WRAPPER) QueryWrapper<UserBookVO> queryWrapper);
}
