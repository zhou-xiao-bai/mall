package com.xiaobai.mall.admin.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobai.mall.dto.TbItemDto;
import com.xiaobai.mall.pms.entity.TbItem;
import com.xiaobai.mall.pms.service.TbItemService;
import com.xiaobai.mall.utils.ResultUtil;
import com.xiaobai.mall.vo.DataTablesResultVo;
import com.xiaobai.mall.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 江湖人称白玉汤
 * @program 毕业设计
 * @description 商品相关 controller
 * @create 2020-03-11 22:44
 **/
@RestController
public class TbItemController {

    @Reference
    private TbItemService tbItemService;

    //获取客户端需要排序的列
    private static final String[] COLS = {"checkbox","id", "image", "title", "sell_point", "price", "created", "updated", "status"};
    
    @GetMapping("/item/{id}")
    public Result<TbItem> getItem(@PathVariable(required = true) Long id) {
        TbItem item = tbItemService.getItem(id);
        return new ResultUtil<TbItem>().setData(item);
    }
    
        
    /**
     * 返回商品总数
     * @return
     */
    @GetMapping("/item/count")
    public DataTablesResultVo getItemCount() {
        DataTablesResultVo resultVo = new DataTablesResultVo();
        Integer itemCount = tbItemService.getItemCount();
        resultVo.setRecordsTotal(itemCount);
        return resultVo;
    }

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("/item/list")
    public DataTablesResultVo getItemList(int draw, int start, int length, int cid, @RequestParam("search[value]") String search,
                            @RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir,
                            String searchItem, String minDate, String maxDate){

        String orderColumn = COLS[orderCol];
        if(orderColumn == null) {
            orderColumn = "created";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        DataTablesResultVo result = tbItemService.getItemList(draw,start,length,cid,search,orderColumn,orderDir);
        return result;
    }

    @GetMapping("/item/listSearch")
    public DataTablesResultVo getItemSearchList(int draw, int start, int length,int cid,String searchKey,String minDate,String maxDate,
                                              @RequestParam("search[value]") String search, @RequestParam("order[0][column]") int orderCol,
                                              @RequestParam("order[0][dir]") String orderDir){
        //默认排序列
        String orderColumn = COLS[orderCol];
        if(orderColumn == null) {
            orderColumn = "created";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        if(!search.isEmpty()){
            searchKey=search;
        }
        DataTablesResultVo result= tbItemService.getItemSearchList(draw,start,length,cid,searchKey,minDate,maxDate,orderColumn,orderDir);
        return result;
    }

    /**
     * 添加商品
     * @param tbItemDto
     * @return
     */
    @PostMapping("/item/add")
    public Result<TbItem> addItem(TbItemDto tbItemDto) {
        System.out.println(tbItemDto);
        TbItem tbItem = tbItemService.addItem(tbItemDto);
        return new ResultUtil<TbItem>().setData(tbItem);
    }

    /**
     * 修改商品状态
     * @param id
     * @return
     */
    @PutMapping("/item/start/{id}")
    public Result<TbItem> startItem(@PathVariable(required = true) Long id) {
        TbItem tbItem = tbItemService.startItem(id);
        return new ResultUtil<TbItem>().setData(tbItem);
    }
    /**
     * 修改商品状态
     * @param id
     * @return
     */
    @PutMapping("/item/stop/{id}")
    public Result<TbItem> stopItem(@PathVariable(required = true) Long id) {
        TbItem tbItem = tbItemService.stopItem(id);
        return new ResultUtil<TbItem>().setData(tbItem);
    }
    @PostMapping("/item/update/{id}")
    public Result<TbItem> updateItem(@PathVariable(required = true) String id, TbItemDto tbItemDto) {
        TbItem tbItem=tbItemService.updateItem(Long.parseLong(id),tbItemDto);
        return new ResultUtil<TbItem>().setData(tbItem);
    }
    @DeleteMapping(value = "/item/del/{ids}")
    public Result<TbItem> deleteItem(@PathVariable Long[] ids){

        for(Long id:ids){
            tbItemService.deleteItem(id);
        }
        return new ResultUtil<TbItem>().setData(null);
    }

}
