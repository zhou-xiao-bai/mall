package com.xiaobai.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 江湖人称白玉汤
 * @program 毕业设计
 * @description 页面跳转 controller
 * @create 2020-03-11 15:17
 **/
@Controller
public class PageController {

    @GetMapping("/")
    public String toIndex() {
        return "index";
    }
    @GetMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
//    @GetMapping("/welcome")
//    public String toWelcome() {
//        return "welcome";
//    }

//    /**
//     * 去商品界面
//     * @return
//     */
//    @GetMapping("/product-list")
//    public String toProductList() {
//        return "product-list";
//    }
//    /**
//     * 去商品添加界面
//     */
//    @GetMapping("/product-add")
//    public String toProductAdd() {
//        return "product-add";
//    }
}
