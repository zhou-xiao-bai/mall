package com.xiaobai.mall.pms.service;

import com.xiaobai.mall.pms.entity.TbPanel;
import com.xiaobai.mall.vo.ZTreeNodeVo;

import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 首页板块
 * @create 2020-04-02 11:54
 **/
public interface TbPanelService {


    List<ZTreeNodeVo> getPanelList();

    void updatePanel(TbPanel tbPanel);

    void addPanel(TbPanel tbPanel);

    void delPanelById(Integer id);

    ZTreeNodeVo getCarousel();
}
