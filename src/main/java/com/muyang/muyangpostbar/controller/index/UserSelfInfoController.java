package com.muyang.muyangpostbar.controller.index;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muyang.muyangpostbar.pojo.User;
import com.muyang.muyangpostbar.service.UserService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-26 12:38:33
 */
@RestController
@RequestMapping("/index/userSelfInfo")
public class UserSelfInfoController {

    @Autowired
    private UserService userService;


    @PostMapping("/getUserInfo")
    public Result getUserInfo(@RequestBody User user){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", user.getEmail());
        wrapper.eq("password", user.getPassword());
        User userInfo = userService.getOne(wrapper);
        return Result.success(userInfo);
    }

    @PostMapping("/uploadHead")
    public Result uploadHead(MultipartFile file) throws IOException {
        Result result = userService.changeUserHead(file);
        return result;
    }

    @PostMapping("/chanegUserHead")
    public Result chanegUserHead(@RequestBody User user){
        userService.updateById(user);
        return Result.success();
    }

    @PostMapping("/changeNickName")
    public Result changeNickName(@RequestBody User user){
        userService.updateById(user);
        return Result.success();
    }

    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody User user){
        userService.updateById(user);
        return Result.success();
    }
}
