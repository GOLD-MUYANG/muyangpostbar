package com.muyang.muyangpostbar.controller.index;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muyang.muyangpostbar.mapper.BarMapper;
import com.muyang.muyangpostbar.pojo.Bar;
import com.muyang.muyangpostbar.service.BarService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-27 15:13:49
 */
@RestController
@RequestMapping("/index/bar")
public class IndexBarController {

    @Autowired
    private BarService barService;
    @Autowired
    private BarMapper barMapper;

    @GetMapping("/getBar")
    public Result getBar(){
       List<Bar> bars =  barService.getBar();
       return Result.success(bars);
    }

    @PostMapping("/getUserInterestBar")
    public Result getUserInterestBar(@RequestBody Map<String,Integer> info){
        Integer userId = info.get("userId");
        List<Bar> bars = barService.getUserInterestBar(userId);
        return Result.success(bars);
    }

    @PostMapping("/getUserMostInterestBar")
    public Result getUserMostInterestBar(@RequestBody Map<String,Integer> info){
        Integer userId = info.get("userId");
        List<Bar> bars = barService.getUserMostInterestBar(userId);
        return Result.success(bars);
    }
}
