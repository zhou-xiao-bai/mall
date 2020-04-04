package com.xiaobai.mall.admin.oms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobai.mall.oms.service.TbOrderService;
import com.xiaobai.mall.utils.ResultUtil;
import com.xiaobai.mall.vo.DataTablesResultVo;
import com.xiaobai.mall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 订单controller
 * @create 2020-03-27 10:36
 **/
@RestController
@RequestMapping("/order")
public class TbOrderController {

    @Reference
    private TbOrderService tbOrderService;
    private static final String[] COLS = {"checkbox","order_id", "payment","shipping_code", "user_id", "buyer_nick", "create_time", "update_time", "payment_time", "close_time","end_time","status"};

    /**
     * 主页获取订单数
     * @return
     */
    @GetMapping("/count")
    public DataTablesResultVo getMemberCount() {
        DataTablesResultVo resultVo = new DataTablesResultVo();
        Integer orderCount = tbOrderService.getOrderCount();
        resultVo.setRecordsTotal(orderCount);
        resultVo.setSuccess(true);
        return resultVo;
    }
    //获取订单列表
    @GetMapping(value = "/list")
    public DataTablesResultVo getOrderList(Integer draw, Integer start, Integer length,@RequestParam("search[value]") String search,
                                         @RequestParam("order[0][column]") Integer orderCol, @RequestParam("order[0][dir]") String orderDir){

        //默认排序列
        String orderColumn = "create_time";
        //获取客户端需要排序的列
        orderColumn = COLS[orderCol];
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        return tbOrderService.getOrderList(draw,start,length,search,orderColumn,orderDir);
    }
    @PostMapping("/deliver")
    public Result<Object> orderDeliver(@RequestParam String orderId, @RequestParam String shippingName,
                                       @RequestParam String shippingCode, @RequestParam BigDecimal postFee) {
        tbOrderService.orderDeliver(orderId, shippingName, shippingCode, postFee);
        return new ResultUtil<Object>().setData(null);
    }
    @PostMapping("/remark")
    public Result<Object> remark(@RequestParam String orderId,
                                 @RequestParam(required = false) String message){

        tbOrderService.remark(orderId,message);
        return new ResultUtil<Object>().setData(null);
    }
    @GetMapping("/cancel/{id}")
    public Result<Object> cancelOrderByAdmin(@PathVariable("id") String orderId){
        tbOrderService.cancelOrderByAdmin(orderId);
        return new ResultUtil<Object>().setData(null);
    }
    @DeleteMapping("/del/{ids}")
    public Result<Object> delOrders(@PathVariable("ids") String[] orderIds){
        for (String orderId:orderIds) {
            tbOrderService.delOrder(orderId);
        }
        return new ResultUtil<Object>().setData(null);
    }

}
