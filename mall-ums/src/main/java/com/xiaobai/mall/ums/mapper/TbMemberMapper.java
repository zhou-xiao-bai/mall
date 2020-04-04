package com.xiaobai.mall.ums.mapper;


import com.xiaobai.mall.ums.entity.TbMember;

import java.util.List;

public interface TbMemberMapper {

    TbMember queryTbMemberById(Long id);

    Integer queryTbMemberCount(Integer state);

    List<TbMember> selectByMemberInfo(String search, String minDate, String maxDate, String orderColumn, String orderDir);

    List<TbMember> selectByRemoveMemberInfo(String search, String minDate, String maxDate, String orderColumn, String orderDir);

    Integer updateMember(TbMember tbMember);

    TbMember queryTbMemberByUserName(String userName);

    TbMember queryTbMemberByPhone(String phone);

    TbMember queryTbMemberByEmail(String email);

    Integer insertMember(TbMember tbMember);

    Integer deleteByPrimaryKey(Long id);
}