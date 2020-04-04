package com.xiaobai.mall.oms.mapper;

import com.xiaobai.mall.oms.entity.TbOrder;

import java.util.List;

public interface TbOrderMapper {

    Integer queryOrderCount();

    List<TbOrder> selectByMulti(String search, String orderColumn, String orderDir);

    Integer updateOrder(TbOrder tbOrder);

    TbOrder selectByPrimaryKey(String orderId);

    Integer deleteOrder(String orderId);
}