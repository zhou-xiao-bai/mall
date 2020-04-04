package com.xiaobai.mall.oms.service;

import com.xiaobai.mall.vo.DataTablesResultVo;

import java.math.BigDecimal;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @create 2020-03-27 10:38
 **/
public interface TbOrderService {

    Integer getOrderCount();

    DataTablesResultVo getOrderList(Integer draw, Integer start, Integer length, String search, String orderColumn, String orderDir);

    void orderDeliver(String orderId, String shippingName, String shippingCode, BigDecimal postFee);

    void remark(String orderId, String message);

    void cancelOrderByAdmin(String orderId);

    void delOrder(String orderId);
}
