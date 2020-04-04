package com.xiaobai.mall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobai.mall.constant.ItemState;
import com.xiaobai.mall.dto.TbItemDto;
import com.xiaobai.mall.exception.MallException;
import com.xiaobai.mall.pms.entity.TbItem;
import com.xiaobai.mall.pms.entity.TbItemDesc;
import com.xiaobai.mall.pms.mapper.TbItemDescMapper;
import com.xiaobai.mall.pms.mapper.TbItemMapper;
import com.xiaobai.mall.pms.service.TbItemService;
import com.xiaobai.mall.utils.DtoUtil;
import com.xiaobai.mall.utils.IDUtil;
import com.xiaobai.mall.vo.DataTablesResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 商品服务实现类
 * @create 2020-03-27 08:58
 **/
@Service
@Component
public class TbItemServiceImpl implements TbItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public Integer getItemCount() {
        return tbItemMapper.queryTbItemCount();
    }

    @Override
    public DataTablesResultVo getItemList(int draw, int start, int length, int cid, String search, String orderColumn, String orderDir) {
        DataTablesResultVo result=new DataTablesResultVo();
        //分页执行查询返回结果
        PageHelper.startPage(start/length+1,length);
        List<TbItem> list = tbItemMapper.selectItemByCondition(cid,"%"+search+"%",orderColumn,orderDir);
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getTotal());
        result.setRecordsTotal(getItemCount());
        result.setDraw(draw);
        result.setData(list);
        return result;
    }

    @Override
    public DataTablesResultVo getItemSearchList(int draw, int start, int length, int cid, String search,
                                                String minDate, String maxDate, String orderColumn, String orderDir) {
        DataTablesResultVo result=new DataTablesResultVo();
        //分页执行查询返回结果
        PageHelper.startPage(start/length+1,length);
        List<TbItem> list = tbItemMapper.selectItemByMultiCondition(cid,"%"+search+"%",minDate,maxDate,orderColumn,orderDir);
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getTotal());
        result.setRecordsTotal(getItemCount());
        result.setDraw(draw);
        result.setData(list);
        return result;
    }

    @Transactional
    @Override
    public TbItem addItem(TbItemDto tbItemDto) {
        long id = IDUtil.getRandomId();
        TbItem tbItem = DtoUtil.ItemDto2TbItem(tbItemDto);
        tbItem.setId(id);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        if (tbItemMapper.insertItem(tbItem) != 1) throw new MallException("添加商品失败");
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(tbItemDto.getDetail());
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        if (tbItemDescMapper.insertItemDesc(tbItemDesc) != 1) throw new MallException("添加商品描述失败");
        return tbItem;
    }

    @Override
    public TbItem startItem(Long id) {
        if (tbItemMapper.updateTbItemStatus(id, ItemState.ONLINE) != 1)
            throw new MallException("修改商品状态异常");
        return getItem(id);
    }

    @Override
    public TbItem stopItem(Long id) {
        if (tbItemMapper.updateTbItemStatus(id, ItemState.OFFLINE) != 1)
            throw new MallException("修改商品状态异常");
        return getItem(id);
    }

    @Transactional
    @Override
    public TbItem updateItem(Long id, TbItemDto tbItemDto) {
        TbItem oldTbItem=getItem(id);

        TbItem tbItem= DtoUtil.ItemDto2TbItem(tbItemDto);

        if(tbItem.getImage().isEmpty()){
            tbItem.setImage(oldTbItem.getImage());
        }
        tbItem.setId(id);
        tbItem.setStatus(oldTbItem.getStatus());
        tbItem.setCreated(oldTbItem.getCreated());
        tbItem.setUpdated(new Date());
        if(tbItemMapper.updateByPrimaryKey(tbItem)!=1){
            throw new MallException("更新商品失败");
        }

        TbItemDesc tbItemDesc=new TbItemDesc();

        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(tbItemDto.getDetail());
        tbItemDesc.setUpdated(new Date());
        tbItemDesc.setCreated(oldTbItem.getCreated());

        if(tbItemDescMapper.updateByPrimaryKey(tbItemDesc)!=1){
            throw new MallException("更新商品详情失败");
        }
        return tbItem;
    }

    @Transactional
    @Override
    public void deleteItem(Long id) {
        if (tbItemMapper.deleteItem(id) != 1) throw new MallException("删除商品失败");
        if (tbItemDescMapper.deleteItem(id) != 1) throw new MallException("删除商品描述失败");
    }

    @Override
    public TbItem getItem(Long id) {
        return tbItemMapper.selectItemById(id);
    }
}
