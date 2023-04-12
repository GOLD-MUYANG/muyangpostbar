package com.muyang.muyangpostbar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyang.muyangpostbar.mapper.InvitationMapper;
import com.muyang.muyangpostbar.pojo.Bar;
import com.muyang.muyangpostbar.pojo.Invitation;
import com.muyang.muyangpostbar.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-28 15:03:40
 */
@Service
public class InvitationServiceImpl extends ServiceImpl<InvitationMapper, Invitation> implements InvitationService {

    @Autowired
    private InvitationMapper invitationMapper;

    @Override
    public Page<Invitation> getInvitataionBySearchPage(Integer pageNumber, Map<String, String> info) {
        QueryWrapper<Invitation> wrapper = new QueryWrapper<>();
        if (info.get("searchWord") != null && !info.get("searchWord").equals("")) {
            wrapper.like("topic", info.get("searchWord")).or()
                    .like("content", info.get("searchWord")).or()
                    .like("belong_user_email", info.get("searchWord")).or()
                    .like("belong_bar_name", info.get("searchWord"));
        }
        //查询第几页，每页几个数据
        Page<Invitation> page = new Page<>(pageNumber, 12);
        Page<Invitation> invitationPage = invitationMapper.selectPage(page, wrapper);
        return invitationPage;
    }
}
