package com.xiaobai.mall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaobai.mall.exception.MallException;
import com.xiaobai.mall.pms.entity.TbItemCat;
import com.xiaobai.mall.pms.mapper.TbItemCatMapper;
import com.xiaobai.mall.pms.service.TbItemCatService;
import com.xiaobai.mall.utils.DtoUtil;
import com.xiaobai.mall.vo.ZTreeNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业设计
 * @description 商品分类服务
 * @create 2020-03-12 15:42
 **/
@Component
@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    /*
        获取商品全部分类
     */
    @Override
    public List<ZTreeNodeVo> getItemCatList(int parentId) {
        List<TbItemCat> list = tbItemCatMapper.getItemCatList(parentId);

        //转换成ZtreeNode
        List<ZTreeNodeVo> resultList=new ArrayList<>();
        ZTreeNodeVo zTreeNodeVo = null;
        for(TbItemCat tbItemCat:list){

            zTreeNodeVo= DtoUtil.TbItemCat2ZTreeNodeVo(tbItemCat);

            resultList.add(zTreeNodeVo);
        }

        return resultList;
    }

    @Override
    public void addItemCat(TbItemCat tbItemCat) {
        if(tbItemCat.getParentId()==0){
            //根节点
            tbItemCat.setSortOrder(0);
            tbItemCat.setStatus(1);
        }else{
            TbItemCat parent=tbItemCatMapper.selectByPrimaryKey(tbItemCat.getParentId());
            tbItemCat.setSortOrder(0);
            tbItemCat.setStatus(1);
            tbItemCat.setCreated(new Date());
            tbItemCat.setUpdated(new Date());
        }
        if(tbItemCatMapper.insertTbItemCat(tbItemCat)!=1){
            throw new MallException("添加商品分类失败");
        }
    }

    @Override
    public void updateItemCat(TbItemCat tbItemCat) {
        TbItemCat old=getItemCatById(tbItemCat.getId());
        tbItemCat.setCreated(old.getCreated());
        tbItemCat.setUpdated(new Date());

        if(tbItemCatMapper.updateByPrimaryKey(tbItemCat)!=1){
            throw new MallException("添加商品分类失败");
        }
    }
    @Override
    public TbItemCat getItemCatById(Long id) {
        TbItemCat tbItemCat=tbItemCatMapper.selectByPrimaryKey(id);
        if(tbItemCat==null){
            throw new MallException("通过id获取商品分类失败");
        }
        return tbItemCat;
    }

    @Override
    public void deleteItemCat(Long id) {
        deleteZTree(id);
    }

    @Override
    public void deleteZTree(Long id) {

        //查询该节点所有子节点
        List<ZTreeNodeVo> node=getItemCatList(Math.toIntExact(id));
        if(node.size()>0){
            //如果有子节点，遍历子节点
            for(int i=0;i<node.size();i++){
                deleteItemCat((long) node.get(i).getId());
            }
        }
        //没有子节点直接删除
        if(tbItemCatMapper.deleteByPrimaryKey(id)!=1){
            throw new MallException("删除商品分类失败");
        }
    }
}
