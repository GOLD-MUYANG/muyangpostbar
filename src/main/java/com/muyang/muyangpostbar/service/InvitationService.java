package com.muyang.muyangpostbar.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muyang.muyangpostbar.pojo.Invitation;

import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-28 15:02:57
 */
public interface InvitationService extends IService<Invitation> {
    Page<Invitation> getInvitataionBySearchPage(Integer pageNumber, Map<String, String> info);
}
