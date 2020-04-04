package com.xiaobai.mall.front.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobai.mall.constant.PanelType;
import com.xiaobai.mall.dto.TbContentDto;
import com.xiaobai.mall.pms.service.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 页面跳转controller
 * @create 2020-03-29 15:02
 **/
@Controller
public class PageController {
    @Reference
    private TbContentService tbContentService;

    @GetMapping("/")
    public String toIndex(Model model) {
        List<TbContentDto> tbContentDtoList = tbContentService.getContentDtoList(PanelType.CROUSEL_TYPE);
        model.addAttribute("carousels", tbContentDtoList);
        return "mall/index";
    }

    @GetMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return "mall/" + page;
    }

}
