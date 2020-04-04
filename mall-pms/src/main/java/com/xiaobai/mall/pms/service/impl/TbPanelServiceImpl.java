package com.xiaobai.mall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaobai.mall.exception.MallException;
import com.xiaobai.mall.pms.entity.TbPanel;
import com.xiaobai.mall.pms.mapper.TbPanelMapper;
import com.xiaobai.mall.pms.service.TbPanelService;
import com.xiaobai.mall.utils.DtoUtil;
import com.xiaobai.mall.utils.IDUtil;
import com.xiaobai.mall.vo.ZTreeNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 首页板块service
 * @create 2020-04-02 12:14
 **/
@Component
@Service
public class TbPanelServiceImpl implements TbPanelService {

    @Autowired
    private TbPanelMapper tbPanelMapper;

    @Override
    public List<ZTreeNodeVo> getPanelList() {
        List<TbPanel> tbPanelList = tbPanelMapper.getList();
        List<ZTreeNodeVo> zTreeNodeVoList = new ArrayList<>();
        for (TbPanel tbPanel : tbPanelList) {
            ZTreeNodeVo zTreeNodeVo = DtoUtil.TbPanel2ZTreeNodeVo(tbPanel);
            zTreeNodeVoList.add(zTreeNodeVo);
        }
        return zTreeNodeVoList;
    }

    @Override
    public void updatePanel(TbPanel tbPanel) {
        tbPanel.setUpdated(new Date());
        if(1 != tbPanelMapper.updatePanel(tbPanel)) throw new MallException("更改首页板块异常");
    }

    @Override
    public void addPanel(TbPanel tbPanel) {
        Integer maxType = tbPanelMapper.queryMaxType();
        tbPanel.setId((int) IDUtil.getRandomId());
        tbPanel.setType(maxType + 1);
        tbPanel.setCreated(new Date());
        tbPanel.setUpdated(new Date());
        if (tbPanelMapper.insetPanel(tbPanel) != 1) throw new MallException("添加首页板块失败");
    }

    @Override
    public void delPanelById(Integer id) {
        if (1 != tbPanelMapper.delPanelById(id)) throw new MallException("删除首页板块失败");
    }

    @Override
    public ZTreeNodeVo getCarousel() {
        TbPanel carousel = tbPanelMapper.getCarousel();
        return DtoUtil.TbPanel2ZTreeNodeVo(carousel);
    }
}
