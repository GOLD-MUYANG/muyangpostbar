package com.muyang.muyangpostbar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyang.muyangpostbar.mapper.BarMapper;
import com.muyang.muyangpostbar.pojo.Bar;
import com.muyang.muyangpostbar.service.BarService;
import com.muyang.muyangpostbar.service.UserInterestBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-27 15:18:27
 */
@Service
public class BarServiceImpl extends ServiceImpl<BarMapper, Bar> implements BarService {
    @Autowired
    private BarMapper barMapper;

    @Autowired
    UserInterestBarService userInterestBarService;

    @Override
    public List<Bar> getBar() {
        List<Bar> bars = barMapper.getBar();
        return bars;
    }

    @Override
    public List<Bar> getUserMostInterestBar(Integer info) {
        List<Bar> bars = barMapper.getUserMostInterestBar(info);
        return bars;
    }

    @Override
    public Page<Bar> getBarBySearchPage(Integer pageNumber, Map<String, String> info) {
        QueryWrapper<Bar> wrapper = new QueryWrapper<>();
        if (info.get("searchWord") != null && !info.get("searchWord").equals("")) {
            wrapper.and(queryWrapper ->
                    queryWrapper.like("bar_name", info.get("searchWord")).or()
                            .like("bar_host", info.get("searchWord")).or()
                            .like("bar_introduction ", info.get("searchWord"))
            );
        }
        //查询第几页，每页几个数据
        Page<Bar> page = new Page<>(pageNumber, 8);
        Page<Bar> barPage = barMapper.selectPage(page, wrapper);
        return barPage;
    }

    @Override
    public List getBarIncrease() {
        List a;
        int numD1 = barMapper.getBarD1();
        int numD2 = barMapper.getBarD2();
        int numD3 = barMapper.getBarD3();
        int numD4 = barMapper.getBarD4();
        int numD5 = barMapper.getBarD5();
        int numD6 = barMapper.getBarD6();
        int numD7 = barMapper.getBarD7();
        a = Arrays.asList(numD7, numD6, numD5, numD4, numD3, numD2, numD1);
        return a;
    }

    @Override
    public int checkIsRegard(Integer userId, Integer barId) {
        QueryWrapper wrapper  = new QueryWrapper();
        wrapper.eq("user_id", userId);
        wrapper.eq("bar_id", barId);
        int count = userInterestBarService.count(wrapper);
        return count;
    }

    @Override
    public List<Bar> getUserInterestBar(Integer userId) {
        List<Bar> bars = barMapper.getUserInterestBar(userId);
        return bars;
    }

}
