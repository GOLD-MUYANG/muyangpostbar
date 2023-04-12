package com.muyang.muyangpostbar.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.mapper.BarMapper;
import com.muyang.muyangpostbar.mapper.CommentMapper;
import com.muyang.muyangpostbar.mapper.InvitationMapper;
import com.muyang.muyangpostbar.pojo.*;
import com.muyang.muyangpostbar.service.*;
import com.muyang.muyangpostbar.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-04-01 18:19:23
 */
@Api
@RestController
@RequestMapping("/index/barView")
public class BarViewController {

    @Autowired
    BarService barService;

    @Autowired
    BarMapper barMapper;

    @Autowired
    InvitationMapper invitationMapper;
    @Autowired
    UserService userService;

    @Autowired
    InvitationService invitationService;

    @Autowired
    UserInterestBarService userInterestBarService;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserInvitationCommentService userInvitationCommentService;

    @GetMapping("/getBarInfomation")
    public Result getBarInfomation(@RequestParam() String barName){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("bar_name", barName);
        Bar bar = barService.getOne(wrapper);
        return Result.success(bar);
    }

    @PostMapping("/checkIsRegard")
    public Result checkIsRegard(@RequestBody Map<String,Integer> info){
        Integer userId = info.get("userId");
        Integer barId = info.get("barId");
        int count = barService.checkIsRegard(userId,barId);
        return Result.success(count);
    }

    @PostMapping("/getBarInvitationPage")
    public Result getBarInvitationPage(@RequestBody Map<String, Object> InvitationInfo){
        Integer pageNumber = (Integer) InvitationInfo.get("pageNumber");
        Integer pageSize = (Integer) InvitationInfo.get("pageSize");
        String barName = (String) InvitationInfo.get("barName");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("belong_bar_name",barName);
        Page<Invitation> page = new Page<>(pageNumber,pageSize);
        Page<Invitation> invitationPage = invitationMapper.selectPage(page, wrapper);
        return Result.success(invitationPage);
    }

    @ApiOperation("将吧id和用户id保存")
    @PostMapping("/regardBar")
    public Result regardBar(@RequestBody Map<String,Integer> info){
        Integer userId = info.get("userId");
        Integer barId = info.get("barId");
        UserInterestBar userInterestBar = new UserInterestBar();
        userInterestBarService.saveUserInterestBar(userId,barId);
        return Result.success();
    }

    @PostMapping("/noRegardBar")
    public Result noRegardBar(@RequestBody Map<String,Integer> info){
        Integer userId = info.get("userId");
        Integer barId = info.get("barId");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        wrapper.eq("bar_id", barId);
        userInterestBarService.remove(wrapper);
        return Result.success();
    }


    @PostMapping("/decreaseBarCount")
    public Result decreaseBarCount(){
        barMapper.decreaseBarCount();
        return Result.success();
    }
    @PostMapping("/increaseBarCount")
    public Result increaseBarCount(){
        barMapper.increaseBarCount();
        return Result.success();
    }

    @PostMapping("/publish")
    public Result publish(@RequestBody Invitation invitation){
        String topic = invitation.getTopic();
        String content = invitation.getContent();
        String belongUserEmail = invitation.getBelongUserEmail();
        String belongBarName = invitation.getBelongBarName();
        invitationMapper.saveInvitation(topic,content,belongUserEmail,belongBarName);
        return Result.success();
    }

    @PostMapping("/publishComment")
    public Result publishComment(@RequestBody Comment comment){
        commentService.save(comment);
        return Result.success();
    }

    @PostMapping("/getInvitationComment")
    public Result getInvitationComment(@RequestBody Map<String,Integer> info){
        Integer invitationId = info.get("invitationId");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("belong_invitation", invitationId);
        List<UserInvitationComment> lists = userInvitationCommentService.getComment(invitationId);
        return Result.success(lists);
    }

    @PostMapping("/deleteComment")
    public Result deleteComment(@RequestBody Comment comment){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", comment.getId());
        commentService.remove(wrapper);
        return Result.success();
    }
}
