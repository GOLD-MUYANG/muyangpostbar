package com.muyang.muyangpostbar.controller.manage;

import com.muyang.muyangpostbar.pojo.Invitation;
import com.muyang.muyangpostbar.pojo.User;
import com.muyang.muyangpostbar.service.BarService;
import com.muyang.muyangpostbar.service.InvitationService;
import com.muyang.muyangpostbar.service.UserService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 18:29:51
 */
@RestController
@RequestMapping("/manage/welcome")
public class WelcomeController {
    @Autowired
    BarService barService;
    @Autowired
    InvitationService invitationService;

    @Autowired
    UserService userService;

    @GetMapping("/getAllBarCount")
    public Result getAllBarCount() {
        int count = barService.count();
        return Result.success(count);
    }

    @GetMapping("/getAllInvitationCount")
    public Result getAllInvitationCount() {
        int count = invitationService.count();
        return Result.success(count);
    }

    @PostMapping("/getBarIncrease")
    public Result getBarIncrease() {
        List num = barService.getBarIncrease();
        return Result.success(num);
    }

    @PostMapping("/getUserIncrease")
    public Result getUserIncrease() {
        List num = userService.getUserIncrease();
        return Result.success(num);
    }
}
