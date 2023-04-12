package com.muyang.muyangpostbar.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muyang.muyangpostbar.pojo.UseComment;

import java.util.List;
import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-30 15:36:38
 */
public interface UseCommentService extends IService<UseComment> {
    Page<UseComment> getCommentBySearchPage(Integer pageNumber, Map<String, String> info);
}
