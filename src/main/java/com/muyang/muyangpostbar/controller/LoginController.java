package com.muyang.muyangpostbar.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muyang.muyangpostbar.pojo.Manager;
import com.muyang.muyangpostbar.pojo.User;
import com.muyang.muyangpostbar.service.ManagerService;
import com.muyang.muyangpostbar.service.UserService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-10 12:47:34
 */


/**
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;


    @PostMapping("/check-user-info")
    public Result checkUserExist(@RequestBody User userInfo){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", userInfo.getEmail());
        wrapper.eq("password", userInfo.getPassword());
        User user = userService.getOne(wrapper);
        if (user != null) {
            return Result.success(user);
        }else{
            return Result.failed("not this user");
        }
    }

    @PostMapping("/check-manager-info")
    public Result checkManagerExist(@RequestBody Manager managerInfo){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", managerInfo.getEmail());
        wrapper.eq("password", managerInfo.getPassword());
        Manager manager = managerService.getOne(wrapper);
        if (manager != null) {
            return Result.success(manager);
        }else{
            return Result.failed("not this manager");
        }
    }

    @PostMapping("/add-user-info")
    public Result addUserInfo(@RequestBody User userInfo){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", userInfo.getEmail());
        wrapper.eq("password", userInfo.getPassword());
        wrapper.eq("real_name", userInfo.getRealName());
        User user = userService.getOne(wrapper);
        if (user==null){
            userInfo.setCreateTime(new Date());
            userInfo.setUpdateTime(new Date());
            userInfo.setState(1);
            userInfo.setHead("morenHead.jpg");
            userService.save(userInfo);
            return Result.success("添加成功");
        }else {
            return Result.failed("添加失败，用户已经存在");
        }
    }

 }
