package com.muyang.muyangpostbar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muyang.muyangpostbar.pojo.Ann;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-20 20:07:41
 */
@Service
public interface AnnService extends IService<Ann> {
    List<Ann> getAnn();
}
