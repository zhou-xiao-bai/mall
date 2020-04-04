package com.xiaobai.mall.pms.mapper;

import com.xiaobai.mall.pms.entity.TbItemCat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface TbItemCatMapper {

    List<TbItemCat> getItemCatList(int parentId);

    Integer insertTbItemCat(TbItemCat tbItemCat);

    TbItemCat selectByPrimaryKey(Long parentId);

    Integer deleteByPrimaryKey(Long id);

    Integer updateByPrimaryKey(TbItemCat tbItemCat);
}