package com.xiaobai.mall.admin.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobai.mall.dto.TbContentDto;
import com.xiaobai.mall.pms.service.TbContentService;
import com.xiaobai.mall.vo.DataTablesResultVo;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 轮播图controller
 * @create 2020-03-29 21:13
 **/
@RestController
@RequestMapping("/content")
public class TbContentController {

    @Reference
    private TbContentService tbContentService;

    @GetMapping("/list/{type}")
    public DataTablesResultVo getCarouselList(@PathVariable Integer type) {
        List<TbContentDto> list = tbContentService.getContentDtoList(type);
        DataTablesResultVo resultVo = new DataTablesResultVo();
        resultVo.setSuccess(true);
        resultVo.setData(list);
        return resultVo;
    }
    @PostMapping("/update")
    public DataTablesResultVo getCarouslList(@PathVariable Integer type) {
        List<TbContentDto> list = tbContentService.getContentDtoList(type);
        DataTablesResultVo resultVo = new DataTablesResultVo();
        resultVo.setSuccess(true);
        resultVo.setData(list);
        return resultVo;
    }

}
