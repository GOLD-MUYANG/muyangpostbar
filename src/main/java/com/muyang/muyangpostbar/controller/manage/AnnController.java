package com.muyang.muyangpostbar.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.mapper.AnnMapper;
import com.muyang.muyangpostbar.pojo.Ann;
import com.muyang.muyangpostbar.service.AnnService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-20 14:20:39
 */
@RestController
@RequestMapping("/manage/ann")
public class AnnController {

    @Autowired
    private AnnService annService;

    @Autowired
    private AnnMapper annMapper;


    @PostMapping("/getAllAnnCount")
    public Result getAllAnnCount() {
        int count = annService.count();
        return Result.success(count);
    }

    @PostMapping("/getAnn/{pageNo}")
    public Result<Page<Ann>> getAnn(@PathVariable("pageNo") Integer pageNo,@RequestBody Map<String, String> info) {
        QueryWrapper<Ann> wrapper = new QueryWrapper<Ann>();
        if (info.get("topicInput")!=null&&!info.get("topicInput").equals("")){
            wrapper.like("topic", info.get("topicInput"));
        }
        if (info.get("managerInput")!=null&&!info.get("managerInput").equals("")){
            wrapper.like("create_manager_id", info.get("managerInput"));
        }
        if (info.get("dataValue")!=null&&!info.get("dataValue").equals("")){
            wrapper.like("create_time", info.get("dataValue"));
        }
        //查询第几页，每页几个数据
        Page<Ann> page = new Page<>(pageNo, 3);
        Page<Ann> userPage = annMapper.selectPage(page, wrapper);
        return Result.success(userPage);
    }

    @PostMapping("/fuzzySearch")
    public Result fuzzySearch(@RequestBody Map<String, String> info) {
        QueryWrapper<Ann> wrapper = new QueryWrapper<Ann>();
        if (info.get("topicInput")!=null&&!info.get("topicInput").equals("")){
            wrapper.like("topic", info.get("topicInput"));
        }
        if (info.get("managerInput")!=null&&!info.get("managerInput").equals("")){
            wrapper.like("create_manager_id", info.get("managerInput"));
        }
        if (info.get("dataValue")!=null&&!info.get("dataValue").equals("")){
            wrapper.like("create_time", info.get("dataValue"));
        }
        Page<Ann> page = new Page<>(1, 3);
        Page<Ann> userPage = annMapper.selectPage(page, wrapper);
        return Result.success(userPage);
    }

    @PostMapping("/getAnnSearchCount")
    public Result gotUserSearchCount(@RequestBody Map<String, String> info) {
        QueryWrapper<Ann> wrapper = new QueryWrapper<Ann>();
        if (info.get("topicInput")!=null&&!info.get("topicInput").equals("")){
            wrapper.like("topic", info.get("topicInput"));
        }
        if (info.get("managerInput")!=null&&!info.get("managerInput").equals("")){
            wrapper.like("create_manager_id", info.get("managerInput"));
        }
        if (info.get("dataValue")!=null&&!info.get("dataValue").equals("")){
            wrapper.like("create_time", info.get("dataValue"));
        }
        int total = annService.count(wrapper);
        return Result.success(total);
    }


    @PostMapping("/saveAnn")
    public Result saveAnn(@RequestBody Ann ann){
        ann.setCreateTime(new Date());
        ann.setUpdateTime(new Date());
        annService.save(ann);
        return Result.success();
    }

    @PostMapping("/deleteAnn")
    public Result<Object> deleteAnn(@RequestBody Ann ann){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", ann.getId());
        annService.remove(wrapper);
        return Result.success();
    }

    @PostMapping("/changeAnn")
    public Result changeAnn(@RequestBody Ann ann){
        ann.setUpdateTime(new Date());
        annService.updateById(ann);
        return Result.success();
    }
}
