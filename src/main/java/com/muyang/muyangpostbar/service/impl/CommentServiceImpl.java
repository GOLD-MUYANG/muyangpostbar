package com.muyang.muyangpostbar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyang.muyangpostbar.mapper.CommentMapper;
import com.muyang.muyangpostbar.pojo.Comment;
import com.muyang.muyangpostbar.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:33:16
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{

}
