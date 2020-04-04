package com.xiaobai.mall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaobai.mall.dto.TbContentDto;
import com.xiaobai.mall.pms.entity.TbContent;
import com.xiaobai.mall.pms.mapper.TbContentMapper;
import com.xiaobai.mall.pms.mapper.TbItemMapper;
import com.xiaobai.mall.pms.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 轮播图服务
 * @create 2020-03-29 21:24
 **/
@Component
@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    TbContentMapper tbContentMapper;
    @Autowired
    TbItemMapper tbItemMapper;

    @Override
    public List<TbContentDto> getContentDtoList(Integer type) {
        List<TbContent> list = tbContentMapper.getListByState(type);
        List<TbContentDto> dtoList = new ArrayList<>();
        for (TbContent tbContent : list) {
            TbContentDto tbContentDto = new TbContentDto();
            tbContentDto.setId(tbContent.getId());
            tbContentDto.setContentUrl(tbContent.getContentUrl());
            tbContentDto.setRedirectUrl(tbContent.getRedirectUrl());
            tbContentDto.setItemId(tbContent.getItemId());
            tbContentDto.setItemTitle(tbItemMapper.selectItemById(tbContent.getItemId()).getTitle());
            tbContentDto.setCreated(tbContent.getCreated());
            tbContentDto.setUpdated(tbContent.getUpdated());
            tbContentDto.setSortOrder(tbContent.getSortOrder());
            dtoList.add(tbContentDto);
        }
        return dtoList;
    }
}
