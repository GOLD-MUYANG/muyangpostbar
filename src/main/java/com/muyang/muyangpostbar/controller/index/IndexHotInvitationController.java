package com.muyang.muyangpostbar.controller.index;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.mapper.InvitationMapper;
import com.muyang.muyangpostbar.pojo.Invitation;
import com.muyang.muyangpostbar.service.InvitationService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-28 14:35:59
 */
@RestController
@RequestMapping("/index/hotInvitation")
public class IndexHotInvitationController {
    @Autowired
    private InvitationService invitationService;

    @Autowired
    private InvitationMapper invitationMapper;

    @PostMapping("/getHotInvitationPage")
    public Result getHotInvitationPage(@RequestBody Map<String, Integer> hotInvitationInfo){
        Integer pageNumber =  hotInvitationInfo.get("pageNumber");
        Integer pageSize =  hotInvitationInfo.get("pageSize");
        Page<Invitation> page = new Page<>(pageNumber,pageSize);
        Page<Invitation> invitationPage = invitationMapper.selectPage(page, new QueryWrapper<>());
        return Result.success(invitationPage);
    }

    @GetMapping("/getHotInvitationTotalPages")
    public Result getHotInvitationTotalPages(){
        int count = invitationService.count();
        return Result.success(count);
    }
}
