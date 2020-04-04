package com.xiaobai.mall.pms.mapper;

import com.xiaobai.mall.pms.entity.TbContent;

import java.util.List;

public interface TbContentMapper {


    List<TbContent> getListByState(Integer type);
}
