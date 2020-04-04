package com.xiaobai.mall.utils;


import cn.hutool.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author 江湖人称白玉汤
 */
public class IPInfoUtil {

    /**
     *天气接口调用者
    */
    private final static String APPID = "86787117";
    private final static String APPSecret = "JjRMc1rw";

    /**
     * 全国天气预报接口
     */
    private final static String GET_WEATHER="https://tianqiapi.com/api?version=v6"+"&appid=" + APPID + "&appsecret=" + APPSecret;

    /**
     * 获取客户端IP地址
     * @param request 请求
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if (inet != null) {
                    ip = inet.getHostAddress();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    //调用接口查询相应 IP 地址天气，并返回天气情况
    public static String getInfo(HttpServletRequest request, String p){
        try {
            return HttpRequest.get(GET_WEATHER + (p == null? "":"&ip=" + p))
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
                    .header("Content-Type", "application/json").execute().body();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
