package com.muyang.muyangpostbar.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyang.muyangpostbar.mapper.UseCommentMapper;
import com.muyang.muyangpostbar.pojo.Comment;
import com.muyang.muyangpostbar.pojo.UseComment;
import com.muyang.muyangpostbar.service.CommentService;
import com.muyang.muyangpostbar.service.UseCommentService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:40:00
 */
@RestController
@RequestMapping("/manage/comment")
public class CommentController {
    @Autowired
    UseCommentService useCommentService;

    @Autowired
    UseCommentMapper useCommentMapper;

    @Autowired
    CommentService commentService;

    @PostMapping("/getCommentBySearchPage/{pageNumber}")
    public Result<Page<UseComment>> getCommentBySearchPage(@PathVariable("pageNumber") Integer pageNumber, @RequestBody Map<String,String> info) {
        Page<UseComment> comments = useCommentService.getCommentBySearchPage(pageNumber,info);
        return Result.success(comments);
    }

    @PostMapping("/deleteComment")
    public Result deleteComment(@RequestBody Comment comment){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", comment.getId());
        commentService.remove(wrapper);
        return Result.success();
    }
}
