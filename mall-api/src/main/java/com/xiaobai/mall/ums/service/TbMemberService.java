package com.xiaobai.mall.ums.service;

import com.xiaobai.mall.dto.TbMemberDto;
import com.xiaobai.mall.ums.entity.TbMember;
import com.xiaobai.mall.vo.DataTablesResultVo;

/**
 * @author 江湖人称白玉汤
 * @program 毕业设计
 * @description 商城用户 service
 * @create 2020-03-10 14:33
 **/
public interface TbMemberService {

    TbMember queryTbMemberById(Long id);

    Integer getMemberCount(Integer status);

    DataTablesResultVo getMemberList(int draw, int start, int length, String search, String minDate, String maxDate, String orderColumn, String orderDir);

    DataTablesResultVo getRemoveMemberList(int draw, int start, int length, String search, String minDate, String maxDate, String orderColumn, String orderDir);

    TbMember updateMemberStatus(Long id, Integer state);

    Integer getRemoveMemberCount();

    TbMember getMemberByUserName(String userName);

    TbMember getMemberByPhone(String phone);

    TbMember getMemberByEmail(String email);

    TbMember insertMember(TbMember tbMember);

    TbMember changePassMember(Long id, TbMemberDto tbMemberDto);

    TbMember getMemberByEditUsername(Long id, String username);

    TbMember getMemberByEditPhone(Long id, String phone);

    TbMember getMemberByEditEmail(Long id, String email);

    TbMember updateMember(Long id, TbMemberDto memberDto);

    void deleteMember(Long id);
}
