package com.xiaobai.mall.pms.service;

import com.xiaobai.mall.dto.TbItemDto;
import com.xiaobai.mall.pms.entity.TbItem;
import com.xiaobai.mall.vo.DataTablesResultVo;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 商品服务
 * @create 2020-03-27 08:57
 **/
public interface TbItemService {




    Integer getItemCount();

    DataTablesResultVo getItemList(int draw, int start, int length, int cid, String search, String orderColumn, String orderDir);

    DataTablesResultVo getItemSearchList(int draw, int start, int length, int cid, String searchKey, String minDate, String maxDate, String orderColumn, String orderDir);

    TbItem addItem(TbItemDto tbItemDto);

    TbItem startItem(Long id);

    TbItem getItem(Long id);

    TbItem stopItem(Long id);

    TbItem updateItem(Long id, TbItemDto tbItemDto);

    void deleteItem(Long id);
}

