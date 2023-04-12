package com.muyang.muyangpostbar.controller.index;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muyang.muyangpostbar.pojo.IndexAction;
import com.muyang.muyangpostbar.pojo.User;
import com.muyang.muyangpostbar.service.IndexActionService;
import com.muyang.muyangpostbar.service.UserService;
import com.muyang.muyangpostbar.service.impl.UserServiceImpl;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-22 21:33:57
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IndexActionService indexActionService;
    @Autowired
    private UserService userService;


    @GetMapping("/getIndexAction")
    public Result getIndexAction(){
        List<IndexAction> indexActionList = indexActionService.getIndexAction();
        return Result.success(indexActionList);
    }
    @PostMapping("/getUserInfo")
    public Result getUserInfo(@RequestBody User user){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", user.getEmail());
        wrapper.eq("password", user.getPassword());
        User userInfo = userService.getOne(wrapper);
        return Result.success(userInfo);
    }
}
