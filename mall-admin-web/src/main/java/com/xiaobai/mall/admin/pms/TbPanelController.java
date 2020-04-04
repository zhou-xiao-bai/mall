package com.xiaobai.mall.admin.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobai.mall.pms.entity.TbPanel;
import com.xiaobai.mall.pms.service.TbPanelService;
import com.xiaobai.mall.utils.ResultUtil;
import com.xiaobai.mall.vo.Result;
import com.xiaobai.mall.vo.ZTreeNodeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 首页板块controller
 * @create 2020-04-02 11:51
 **/
@RestController
@RequestMapping("/panel")
public class TbPanelController {

    @Reference
    private TbPanelService tbPanelService;

    @GetMapping("/list")
    public List<ZTreeNodeVo> getPanelList() {
        return tbPanelService.getPanelList();
    }
    @PostMapping("/update")
    public Result<Object> updatePanel(@ModelAttribute TbPanel tbPanel) {
        tbPanelService.updatePanel(tbPanel);
        return new ResultUtil<Object>().setData(null);
    }
    @PostMapping("/add")
    public Result<Object> addPanel(@ModelAttribute TbPanel tbPanel) {
        tbPanelService.addPanel(tbPanel);
        return new ResultUtil<Object>().setData(null);
    }
    @DeleteMapping("/del/{id}")
    public Result<Object> delPanelById(@PathVariable Integer id) {
        tbPanelService.delPanelById(id);
        return new ResultUtil<Object>().setData(null);
    }
    @GetMapping("/carousel")
    public ZTreeNodeVo getCarousel() {
        return tbPanelService.getCarousel();
    }

}
