package com.xiaobai.mall.pms.mapper;

import com.xiaobai.mall.pms.entity.TbItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface TbItemMapper {

    public Integer queryTbItemCount();

    List<TbItem> selectItemByCondition(int cid, String search, String orderColumn, String orderDir);

    List<TbItem> selectItemByMultiCondition(int cid, String search, String minDate, String maxDate, String orderColumn, String orderDir);

    Integer insertItem(TbItem tbItem);

    TbItem selectItemById(Long id);

    Integer updateTbItemStatus(Long id, Integer status);

    Integer updateByPrimaryKey(TbItem tbItem);

    Integer deleteItem(Long id);
}