package com.muyang.muyangpostbar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyang.muyangpostbar.mapper.AnnMapper;
import com.muyang.muyangpostbar.pojo.Ann;
import com.muyang.muyangpostbar.service.AnnService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-20 20:08:02
 */
@Service
public class AnnServiceImpl extends ServiceImpl<AnnMapper, Ann> implements AnnService {
    @Autowired
    private AnnMapper annMapper;

    @Override
    public List<Ann> getAnn() {
        return annMapper.getAnn();
    }
}
