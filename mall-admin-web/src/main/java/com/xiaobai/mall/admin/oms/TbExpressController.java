package com.xiaobai.mall.admin.oms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobai.mall.oms.entity.TbExpress;
import com.xiaobai.mall.oms.service.TbExpressService;
import com.xiaobai.mall.vo.DataTablesResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 快递公司controller
 * @create 2020-03-28 10:51
 **/
@RestController
@RequestMapping("/express")
public class TbExpressController {

    @Reference
    private TbExpressService tbExpressService;

    @GetMapping("/list")
    public DataTablesResultVo getExpressList() {
        return tbExpressService.getExpressList();
    }

}
