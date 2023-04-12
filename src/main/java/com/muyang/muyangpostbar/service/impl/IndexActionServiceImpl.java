package com.muyang.muyangpostbar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyang.muyangpostbar.mapper.IndexActionMapper;
import com.muyang.muyangpostbar.pojo.IndexAction;
import com.muyang.muyangpostbar.service.IndexActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-22 21:39:21
 */
@Service
public class IndexActionServiceImpl extends ServiceImpl<IndexActionMapper, IndexAction> implements IndexActionService{
    @Autowired
    private IndexActionMapper indexActionMapper;

    @Override
    public List<IndexAction> getIndexAction() {
        QueryWrapper wrapper = new QueryWrapper();
        return indexActionMapper.selectList(wrapper);
    }
}
