package com.muyang.muyangpostbar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyang.muyangpostbar.mapper.UseCommentMapper;
import com.muyang.muyangpostbar.pojo.UseComment;
import com.muyang.muyangpostbar.service.UseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:37:03
 */
@Service
public class UseCommentServiceImpl extends ServiceImpl<UseCommentMapper, UseComment> implements UseCommentService {

    @Autowired
    UseCommentMapper useCommentMapper;

    @Override
    public Page<UseComment> getCommentBySearchPage(Integer pageNumber, Map<String, String> info) {
        QueryWrapper<UseComment> wrapper = new QueryWrapper();
        String searchWord = info.get("searchWord");
        if (searchWord!=null&&!searchWord.equals("")){
            wrapper.eq("belong_user_id", searchWord).or()
                    .like("belong_bar_id", searchWord).or()
                    .like("content", searchWord);
        }
        Page<UseComment> page = new Page(pageNumber,12);
        Page<UseComment> useComments = useCommentMapper.getCommentBySearchPage(page, wrapper);
        return useComments;
    }
}
