package com.xiaobai.mall.utils;


import com.xiaobai.mall.dto.TbItemDto;
import com.xiaobai.mall.dto.TbMemberDto;
import com.xiaobai.mall.pms.entity.TbItem;
import com.xiaobai.mall.pms.entity.TbItemCat;
import com.xiaobai.mall.pms.entity.TbPanel;
import com.xiaobai.mall.ums.entity.TbMember;
import com.xiaobai.mall.vo.ZTreeNodeVo;

/**
 * @author 江湖人称白玉汤
 * @date 2020/3/20
 */
public class DtoUtil {


    public static ZTreeNodeVo TbItemCat2ZTreeNodeVo(TbItemCat tbItemCat){

        ZTreeNodeVo zTreeNode =new ZTreeNodeVo();

        zTreeNode.setId(Math.toIntExact(tbItemCat.getId()));
        zTreeNode.setStatus(tbItemCat.getStatus());
        zTreeNode.setSortOrder(tbItemCat.getSortOrder());
        zTreeNode.setName(tbItemCat.getName());
        zTreeNode.setpId(Math.toIntExact(tbItemCat.getParentId()));
        zTreeNode.setIsParent(tbItemCat.getIsParent());
        zTreeNode.setRemark(tbItemCat.getRemark());

        return zTreeNode;
    }
    public static TbItem ItemDto2TbItem(TbItemDto itemDto){

        TbItem tbItem =new TbItem();

        tbItem.setTitle(itemDto.getTitle());
        tbItem.setPrice(itemDto.getPrice());
        tbItem.setCid(itemDto.getCid());
        tbItem.setImage(itemDto.getImage());
        tbItem.setSellPoint(itemDto.getSellPoint());
        tbItem.setNum(itemDto.getNum());
        if(itemDto.getLimitNum()==null||itemDto.getLimitNum()<0){
            tbItem.setLimitNum(10);
        }else{
            tbItem.setLimitNum(itemDto.getLimitNum());
        }

        return tbItem;
    }
    public static TbMember MemberDto2Member(TbMemberDto memberDto){

        TbMember tbMember =new TbMember();

        if(!memberDto.getUsername().isEmpty()){
            tbMember.setUsername(memberDto.getUsername());
        }
        if(!memberDto.getPassword().isEmpty()){
            tbMember.setPassword(memberDto.getPassword());
        }
        if(!memberDto.getPhone().isEmpty()){
            tbMember.setPhone(memberDto.getPhone());
        }
        if(!memberDto.getEmail().isEmpty()){
            tbMember.setEmail(memberDto.getEmail());
        }
        if(!memberDto.getGender().isEmpty()){
            tbMember.setGender(memberDto.getGender());
        }
        if(!memberDto.getDescription().isEmpty()){
            tbMember.setDescription(memberDto.getDescription());
        }
        if(!memberDto.getProvince().isEmpty()){
            tbMember.setAddress(memberDto.getProvince()+" "
                    +memberDto.getCity()+" "+memberDto.getDistrict());
        }

        return tbMember;
    }

    public static ZTreeNodeVo TbPanel2ZTreeNodeVo(TbPanel tbPanel) {
        ZTreeNodeVo zTreeNode =new ZTreeNodeVo();

        zTreeNode.setId(tbPanel.getId());
        zTreeNode.setIsParent(false);
        zTreeNode.setpId(0);
        zTreeNode.setName(tbPanel.getName());
        zTreeNode.setSortOrder(tbPanel.getSortOrder());
        zTreeNode.setStatus(tbPanel.getState());
        zTreeNode.setRemark(tbPanel.getRemark());
        zTreeNode.setLimitNum(tbPanel.getLimitNum());
        zTreeNode.setType(tbPanel.getType());
        zTreeNode.setRemark(tbPanel.getRemark());
        return zTreeNode;
    }
}
