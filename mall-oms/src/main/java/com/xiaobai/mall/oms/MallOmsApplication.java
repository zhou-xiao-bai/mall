package com.xiaobai.mall.oms;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@MapperScan(basePackages = "com.xiaobai.mall.oms.mapper")
@SpringBootApplication
public class MallOmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOmsApplication.class, args);
    }

}
