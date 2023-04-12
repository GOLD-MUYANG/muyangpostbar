package com.muyang.muyangpostbar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muyang.muyangpostbar.pojo.User;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-12 21:05:55
 */
public interface UserService extends IService<User> {
    Result changeUserHead(MultipartFile file) throws IOException;

    List getUserIncrease();
}
