package com.xiaobai.mall.pms.mapper;

import com.xiaobai.mall.pms.entity.TbPanel;

import java.util.List;

public interface TbPanelMapper {


    List<TbPanel> getList();

    TbPanel selectByPrimaryKey(Integer id);

    Integer updatePanel(TbPanel tbPanel);

    Integer queryMaxType();

    Integer insetPanel(TbPanel tbPanel);

    Integer delPanelById(Integer id);

    TbPanel getCarousel();
}
