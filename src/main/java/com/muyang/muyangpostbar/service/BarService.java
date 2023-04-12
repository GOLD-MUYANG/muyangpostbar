package com.muyang.muyangpostbar.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muyang.muyangpostbar.pojo.Bar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-27 15:18:04
 */
@Service
public interface BarService extends IService<Bar> {
    List<Bar> getBar();

    List<Bar> getUserMostInterestBar(Integer info);

    Page<Bar> getBarBySearchPage(Integer pageNumber, Map<String, String> info);

    List getBarIncrease();


    int checkIsRegard(Integer userId, Integer barId);

    List<Bar> getUserInterestBar(Integer userId);
}
