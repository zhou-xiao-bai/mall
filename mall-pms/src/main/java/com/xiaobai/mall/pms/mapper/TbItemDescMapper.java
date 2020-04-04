package com.xiaobai.mall.pms.mapper;

import com.xiaobai.mall.pms.entity.TbItemDesc;
import org.apache.ibatis.annotations.Mapper;

public interface TbItemDescMapper {

    Integer insertItemDesc(TbItemDesc tbItemDesc);

    Integer updateByPrimaryKey(TbItemDesc tbItemDesc);

    Integer deleteItem(Long itemId);
}