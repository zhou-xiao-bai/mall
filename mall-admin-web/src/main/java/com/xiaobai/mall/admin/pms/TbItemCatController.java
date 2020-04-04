package com.xiaobai.mall.admin.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobai.mall.pms.entity.TbItemCat;
import com.xiaobai.mall.pms.service.TbItemCatService;
import com.xiaobai.mall.utils.ResultUtil;
import com.xiaobai.mall.vo.Result;
import com.xiaobai.mall.vo.ZTreeNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业设计
 * @description 商品分类 controller
 * @create 2020-03-12 15:37
 **/
@RestController
public class TbItemCatController {

    @Reference
    private TbItemCatService tbItemCatService;

    /*
     商品列表附带查询商品分类表
     */
    @GetMapping("/item/cat/list")
    public List<ZTreeNodeVo> getItemCatList(@RequestParam(name="id",defaultValue = "0") int parentId) {
        return tbItemCatService.getItemCatList(parentId);
    }
    //添加商品分类
    @PostMapping(value = "/item/cat/add")
    public Result<Object> addItemCategory(@ModelAttribute TbItemCat tbItemCat){

        tbItemCatService.addItemCat(tbItemCat);
        return new ResultUtil<Object>().setData(null);
    }
    //编辑商品分类
    @PostMapping(value = "/item/cat/update")
    public Result<Object> updateItemCategory(@ModelAttribute TbItemCat tbItemCat){

        tbItemCatService.updateItemCat(tbItemCat);
        return new ResultUtil<Object>().setData(null);
    }
    //删除商品分类
    @DeleteMapping(value = "/item/cat/del/{id}")
    public Result<Object> deleteItemCategory(@PathVariable Long id){

        tbItemCatService.deleteItemCat(id);
        return new ResultUtil<Object>().setData(null);
    }
}
