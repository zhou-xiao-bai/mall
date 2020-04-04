package com.xiaobai.mall.pms;

import com.xiaobai.mall.pms.service.TbItemCatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallPmsApplicationTests {

    @Autowired
    TbItemCatService tbItemCatService;

    @Test
    void contextLoads() {
        System.out.println(tbItemCatService.getItemCatList(1));
    }

}
