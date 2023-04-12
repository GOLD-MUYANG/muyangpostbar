package com.muyang.muyangpostbar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muyang.muyangpostbar.pojo.IndexAction;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-22 21:37:13
 */
public interface IndexActionService extends IService<IndexAction> {
    List<IndexAction> getIndexAction();
}
