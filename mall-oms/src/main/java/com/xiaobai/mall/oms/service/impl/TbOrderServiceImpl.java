package com.xiaobai.mall.oms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobai.mall.constant.OrderState;
import com.xiaobai.mall.exception.MallException;
import com.xiaobai.mall.oms.entity.TbOrder;
import com.xiaobai.mall.oms.mapper.TbOrderMapper;
import com.xiaobai.mall.oms.service.TbOrderService;
import com.xiaobai.mall.vo.DataTablesResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 订单服务
 * @create 2020-03-27 10:38
 **/
@Service
@Component
public class TbOrderServiceImpl implements TbOrderService {

    @Autowired
    TbOrderMapper tbOrderMapper;

    @Override
    public Integer getOrderCount() {
        return tbOrderMapper.queryOrderCount();
    }

    @Override
    public DataTablesResultVo getOrderList(Integer draw, Integer start, Integer length, String search, String orderColumn, String orderDir) {
        DataTablesResultVo result=new DataTablesResultVo();
        //分页
        PageHelper.startPage(start/length+1,length);
        List<TbOrder> list = tbOrderMapper.selectByMulti("%"+search+"%",orderColumn,orderDir);
        PageInfo<TbOrder> pageInfo=new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getTotal());
        result.setRecordsTotal((int) pageInfo.getTotal());
        result.setDraw(draw);
        result.setData(list);
        result.setSuccess(true);
        return result;
    }

    //订单发货
    @Override
    public void orderDeliver(String orderId, String shippingName, String shippingCode, BigDecimal postFee) {
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
        tbOrder.setShippingName(shippingName);
        tbOrder.setShippingCode(shippingCode);
        tbOrder.setPostFee(postFee);
        tbOrder.setConsignTime(new Date());
        tbOrder.setUpdateTime(new Date());
        tbOrder.setStatus(OrderState.SHIPPED);
        if (1 != tbOrderMapper.updateOrder(tbOrder)) throw new MallException("修改订单出错");
    }

    @Override
    public void remark(String orderId, String message) {
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
        tbOrder.setBuyerMessage(message);
        tbOrder.setUpdateTime(new Date());
        tbOrderMapper.updateOrder(tbOrder);
    }

    @Override
    public void cancelOrderByAdmin(String orderId) {
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
        tbOrder.setStatus(OrderState.TRANSACTION_CLOSED);
        if (1 != tbOrderMapper.updateOrder(tbOrder)) throw new MallException("交易关闭失败");
    }

    @Override
    public void delOrder(String orderId) {
        if (1 != tbOrderMapper.deleteOrder(orderId)) throw new MallException("删除订单失败");
    }
}
