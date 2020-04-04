package com.xiaobai.mall.oms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaobai.mall.oms.entity.TbExpress;
import com.xiaobai.mall.oms.mapper.TbExpressMapper;
import com.xiaobai.mall.oms.service.TbExpressService;
import com.xiaobai.mall.vo.DataTablesResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 快递公司服务
 * @create 2020-03-28 10:49
 **/
@Component
@Service
public class TbExpressServiceImpl implements TbExpressService {

    @Autowired
    TbExpressMapper tbExpressMapper;

    @Override
    public DataTablesResultVo getExpressList() {
        List<TbExpress> tbExpressMapperList = tbExpressMapper.getList();
        DataTablesResultVo resultVo = new DataTablesResultVo();
        resultVo.setData(tbExpressMapperList);
        resultVo.setSuccess(true);
        return resultVo;
    }
}
