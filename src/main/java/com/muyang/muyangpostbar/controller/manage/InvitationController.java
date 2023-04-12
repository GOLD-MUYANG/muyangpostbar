package com.muyang.muyangpostbar.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.mapper.InvitationMapper;
import com.muyang.muyangpostbar.pojo.Invitation;
import com.muyang.muyangpostbar.service.InvitationService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-29 20:46:42
 */
@RestController
@RequestMapping("/manage/invitation")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private InvitationMapper invitationMapper;

    @PostMapping("/getInvitaionBySearchPage/{pageNumber}")
    public Result getInvitataionBySearchPage(@PathVariable("pageNumber") Integer pageNumber, @RequestBody Map<String,String> info){
        Page<Invitation> invitationPage = invitationService.getInvitataionBySearchPage(pageNumber,info);
        return Result.success(invitationPage);
    }

    @PostMapping("/deleteInvitation")
    public Result deleteInvitation(@RequestBody Invitation invitation){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", invitation.getId());
        invitationService.remove(wrapper);
        return Result.success();
    }
}
