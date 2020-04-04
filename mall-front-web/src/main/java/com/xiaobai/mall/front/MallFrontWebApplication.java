package com.xiaobai.mall.front;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class MallFrontWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallFrontWebApplication.class, args);
    }

}
