package com.xiaobai.mall.pms.service;

import com.xiaobai.mall.pms.entity.TbItemCat;
import com.xiaobai.mall.vo.ZTreeNodeVo;

import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @description 商品分类服务接口
 * @program 毕业设计
 * @create 2020-03-12 15:40
 **/
public interface TbItemCatService {


    List<ZTreeNodeVo> getItemCatList(int parentId);

    void addItemCat(TbItemCat tbItemCat);

    void updateItemCat(TbItemCat tbItemCat);

    void deleteItemCat(Long id);

    void deleteZTree(Long id);

    TbItemCat getItemCatById(Long id);
}
