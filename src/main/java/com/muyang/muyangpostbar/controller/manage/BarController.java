package com.muyang.muyangpostbar.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.mapper.BarMapper;
import com.muyang.muyangpostbar.pojo.Bar;
import com.muyang.muyangpostbar.service.BarService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-29 16:54:53
 */
@RestController
@RequestMapping("/manage/bar")
public class BarController {
    @Autowired
    private BarService barService;
    @Autowired
    private BarMapper barMapper;

    @PostMapping("getBarBySearchPage/{pageNumber}")
    public Result getBarBySearchPage(@PathVariable("pageNumber") Integer pageNumber,@RequestBody Map<String,String> info){
       Page<Bar> barPage =  barService.getBarBySearchPage(pageNumber,info);
       return Result.success(barPage);
    }

    @PostMapping("/deleteBar")
    public Result deleteBar(@RequestBody Bar bar){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("bar_name", bar.getBarName());
        barService.remove(wrapper);
        return Result.success();
    }
}
