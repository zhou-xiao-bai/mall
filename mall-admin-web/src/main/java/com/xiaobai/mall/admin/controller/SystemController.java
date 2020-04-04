package com.xiaobai.mall.admin.controller;

import com.xiaobai.mall.utils.IPInfoUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xiaobai.mall.utils.IPInfoUtil;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 江湖人称白玉汤
 * @program 毕业设计
 * @description 系统 controller
 * @create 2020-03-11 16:35
 **/

@RestController
//@Api(description= "系统配置管理")
public class SystemController {

    @RequestMapping(value = "/sys/weather",method = RequestMethod.GET)
    public String getWeather(HttpServletRequest request){
        String ipAddr = IPInfoUtil.getIpAddr(request);
        return IPInfoUtil.getInfo(request,ipAddr);
    }
}

