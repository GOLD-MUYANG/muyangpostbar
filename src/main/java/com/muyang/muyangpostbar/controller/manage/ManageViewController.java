package com.muyang.muyangpostbar.controller.manage;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.mapper.UserMapper;
import com.muyang.muyangpostbar.pojo.Manager;
import com.muyang.muyangpostbar.pojo.Role;
import com.muyang.muyangpostbar.pojo.User;
import com.muyang.muyangpostbar.service.ManagerService;
import com.muyang.muyangpostbar.service.UserService;
import com.muyang.muyangpostbar.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-15 21:13:55
 * 此页面包含user，后面的模块会分开写
 */
@RestController
@RequestMapping("/manage")
@Api(value = "管理员主controller，但是写了一些管理user的方法，当时没注意")
public class ManageViewController {

    @Autowired
    ManagerService managerService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @ApiOperation(value = "根据用户邮箱和密码查询用户")
    @PostMapping("/gotManagerInfo")
    public Result<Manager> getManagerInfo(@ApiParam(value = "前端传来的管理员输入信息") @RequestBody Manager manager) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", manager.getEmail());
        wrapper.eq("password", manager.getPassword());
        Manager managerInfo = managerService.getOne(wrapper);
        List<Role> managerRole = managerService.getManagerRoles(managerInfo.getRoleId());
        managerInfo.setRoles(managerRole);
        return Result.success(managerInfo);
    }

    @PostMapping("/gotUser/{pageNo}")
    public Result<Page<User>> gotUser(@PathVariable("pageNo") Integer pageNo,@RequestBody Map<String,String> info) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (info.get("value")!=null&&!info.get("value").equals("")){
            if (info.get("value").equals("正常")) {
                wrapper.eq("state", 1);
                if (info.get("searchWord")!=null&& !info.get("searchWord").equals("")){
                    wrapper.and(queryWrapper->
                            queryWrapper.like("email", info.get("searchWord")).or()
                                    .like("real_name", info.get("searchWord")).or()
                                    .like("nick_name", info.get("searchWord"))
                    );
                }
            } else if (info.get("value").equals("禁用")) {
                wrapper.eq("state", 0);
            }
        }else{
            if (info.get("searchWord")!=null&& !info.get("searchWord").equals("")){
                wrapper.like("email", info.get("searchWord")).or()
                        .like("real_name", info.get("searchWord")).or()
                        .like("nick_name", info.get("searchWord"));
            }
        }
        //查询第几页，每页几个数据
        Page<User> page = new Page<>(pageNo, 8);
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        return Result.success(userPage);
    }

    @GetMapping("/gotUserCount")
    public Result gotUserCount() {
        int count = userService.count();
        return Result.success(count);
    }

    @PostMapping("/gotUserSearchCount")
    public Result gotUserSearchCount(@RequestBody Map<String, String> info) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        if (info.get("value")!=null&&!info.get("value").equals("")){
            if (info.get("value").equals("正常")) {
                wrapper.eq("state", 1);
                if (info.get("searchWord")!=null&& !info.get("searchWord").equals("")){
                    wrapper.and(queryWrapper->
                            queryWrapper.like("email", info.get("searchWord")).or()
                                    .like("real_name", info.get("searchWord")).or()
                                    .like("nick_name", info.get("searchWord"))
                    );
                }
            } else if (info.get("value").equals("禁用")) {
                wrapper.eq("state", 0);
            }
        }
        int count = userService.count(wrapper);
        return Result.success(count);
    }

    @PostMapping("/deleteUser")
    public Result<String> deleteUser(@RequestBody User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", user.getEmail());
        wrapper.eq("password", user.getPassword());
        user.setState(0);
        user.setUpdateTime(new Date());
        int delete = userMapper.update(user, wrapper);
        return Result.success();
    }

    @PostMapping("/gotThisUser")
    public Result gotThisUser(@RequestBody User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", user.getEmail());
        wrapper.eq("password", user.getPassword());
        User one = userService.getOne(wrapper);
        return Result.success(user);
    }

    @PostMapping("/changeToMoRen")
    public Result changeToMoRen(@RequestBody User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", user.getEmail());
        wrapper.eq("password", user.getPassword());
        user.setUpdateTime(new Date());
        userService.update(user, wrapper);
        return Result.success();
    }

    @PostMapping("/fuzzySearch")
    public Result fuzzySearch(@RequestBody Map<String, String> info) {
//        System.out.println(info.get("searchWord")+"\n\n\n\n");
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        if (info.get("value")!=null&&!info.get("value").equals("")){
            if (info.get("value").equals("正常")) {
                wrapper.eq("state", 1);
                //只有状态正常的用户，模糊搜索时才会被搜索到
                if (info.get("searchWord")!=null&& !info.get("searchWord").equals("")){
                    wrapper.and(queryWrapper->
                            queryWrapper.like("email", info.get("searchWord")).or()
                                    .like("real_name", info.get("searchWord")).or()
                                    .like("nick_name", info.get("searchWord"))
                    );
                }
            } else if (info.get("value").equals("禁用")) {
                wrapper.eq("state", 0);
            }
        }else{
            if (info.get("searchWord")!=null&& !info.get("searchWord").equals("")){
                wrapper.like("email", info.get("searchWord")).or()
                        .like("real_name", info.get("searchWord")).or()
                        .like("nick_name", info.get("searchWord"));
            }
        }
        //查询第几页，每页几个数据
        Page<User> page = new Page<>(1, 8);
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        return Result.success(userPage);
    }
}
