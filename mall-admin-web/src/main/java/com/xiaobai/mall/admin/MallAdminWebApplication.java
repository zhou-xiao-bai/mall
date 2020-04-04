package com.xiaobai.mall.admin;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class MallAdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAdminWebApplication.class, args);
    }

}
