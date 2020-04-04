package com.xiaobai.mall.pms.service;

import com.xiaobai.mall.dto.TbContentDto;

import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 轮播图接口
 * @create 2020-03-29 21:13
 **/
public interface TbContentService {


    List<TbContentDto> getContentDtoList(Integer type);
}
