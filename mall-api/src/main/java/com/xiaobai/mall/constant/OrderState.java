package com.xiaobai.mall.constant;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 订单状态
 * @create 2020-03-28 09:37
 **/
public class OrderState {

    //未支付
    public static final Integer TO_BE_PAID = 0;
    //已经支付
    public static final Integer ALREADY_PAID = 1;
    //未发货
    public static final Integer UN_SHIPPED = 2;
    //已发货
    public static final Integer SHIPPED = 3;
    //交易成功
    public static final Integer SUCCESSFUL_TRADE = 4;
    //交易关闭
    public static final Integer TRANSACTION_CLOSED = 5;


}
