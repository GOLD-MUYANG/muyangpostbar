package com.muyang.muyangpostbar;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.mapper.BookMapper;
import com.muyang.muyangpostbar.testp.UserBookVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Slf4j
@SpringBootTest
class MuyangpostbarApplicationTests {

    @Autowired
    BookMapper bookMapper;
    @Test
    void contextLoads() {
        Page<UserBookVO> page = new Page<>(1,5);
        QueryWrapper<UserBookVO> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 2);
        //查询用户id为2的书籍信息。当前分页为1，页大小为5
        List<UserBookVO> bookAndUser = bookMapper.getBookAndUser(page, wrapper);
        log.error("结果：{}", bookAndUser);
        log.error("总数：{}", page.getTotal());
    }

}
