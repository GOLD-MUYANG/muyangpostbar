package com.muyang.muyangpostbar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyang.muyangpostbar.mapper.UserMapper;
import com.muyang.muyangpostbar.pojo.User;
import com.muyang.muyangpostbar.service.UserService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-12 21:05:40
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public Result changeUserHead(MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return Result.failed("没有选择文件");
        }
        //把图片放在这个目录
        String headName = UUID.randomUUID()+file.getOriginalFilename();
        File f = new File("D:/muyangpostbar/vue/src/assets/userHead/"+ headName);
        byte[] arr = file.getBytes();
        file.transferTo(f);
        return Result.success(headName);
    }

    @Override
    public List getUserIncrease() {
        List a;
        int numD1 = userMapper.getUserD1();
        int numD2 = userMapper.getUserD2();
        int numD3 = userMapper.getUserD3();
        int numD4 = userMapper.getUserD4();
        int numD5 = userMapper.getUserD5();
        int numD6 = userMapper.getUserD6();
        int numD7 = userMapper.getUserD7();
        a = Arrays.asList(numD7,numD6,numD5,numD4,numD3,numD2,numD1);
        return a;
    }
}
