package com.xiaobai.mall.ums.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobai.mall.constant.MemberState;
import com.xiaobai.mall.dto.TbMemberDto;
import com.xiaobai.mall.exception.MallException;
import com.xiaobai.mall.ums.entity.TbMember;
import com.xiaobai.mall.ums.mapper.TbMemberMapper;
import com.xiaobai.mall.ums.service.TbMemberService;
import com.xiaobai.mall.utils.DtoUtil;
import com.xiaobai.mall.utils.IDUtil;
import com.xiaobai.mall.vo.DataTablesResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author 江湖人称白玉汤
 * @program 毕业设计
 * @description 商城用户 service
 * @create 2020-03-10 14:35
 **/
@Component
@Service
public class TbMemberServiceImpl implements TbMemberService {

    @Autowired
    private TbMemberMapper tbMemberMapper;

    @Override
    public TbMember queryTbMemberById(Long id) {
        return tbMemberMapper.queryTbMemberById(id);
    }

    @Override
    public Integer getMemberCount(Integer state) {
        return tbMemberMapper.queryTbMemberCount(state);
    }

    @Override
    public DataTablesResultVo getMemberList(int draw, int start, int length, String search, String minDate, String maxDate, String orderColumn, String orderDir) {
        DataTablesResultVo result=new DataTablesResultVo();

        try{
            //分页
            PageHelper.startPage(start/length+1,length);
            List<TbMember> list = tbMemberMapper.selectByMemberInfo("%"+search+"%",minDate,maxDate,orderColumn,orderDir);
            PageInfo<TbMember> pageInfo=new PageInfo<>(list);
            for(TbMember tbMember:list){
                tbMember.setPassword("");
            }
            result.setRecordsFiltered((int)pageInfo.getTotal());
            result.setRecordsTotal(getMemberCount(MemberState.ENABLE)+getMemberCount(MemberState.UN_ENABLE));
            result.setDraw(draw);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new MallException("加载用户列表失败");
        }
        return result;
    }

    @Override
    public DataTablesResultVo getRemoveMemberList(int draw, int start, int length, String search, String minDate, String maxDate, String orderColumn, String orderDir) {
        DataTablesResultVo result=new DataTablesResultVo();

        try{
            //分页执行查询返回结果
            PageHelper.startPage(start/length+1,length);
            List<TbMember> list = tbMemberMapper.selectByRemoveMemberInfo("%"+search+"%",minDate,maxDate,orderColumn,orderDir);
            PageInfo<TbMember> pageInfo=new PageInfo<>(list);

            for(TbMember tbMember:list){
                tbMember.setPassword("");
            }

            result.setRecordsFiltered((int)pageInfo.getTotal());
            result.setRecordsTotal(getRemoveMemberCount());

            result.setDraw(draw);
            result.setData(list);
        }catch (Exception e){
            throw new MallException("加载删除用户列表失败");
        }

        return result;
    }

    @Override
    public TbMember updateMemberStatus(Long id, Integer state) {
        TbMember tbMember = tbMemberMapper.queryTbMemberById(id);
        tbMember.setState(state);
        if (1 != tbMemberMapper.updateMember(tbMember)) throw new MallException("修改用户状态异常");
        return tbMember;
    }

    @Override
    public Integer getRemoveMemberCount() {
        return tbMemberMapper.queryTbMemberCount(MemberState.REMOVED);
    }

    @Override
    public TbMember getMemberByUserName(String userName) {
       return tbMemberMapper.queryTbMemberByUserName(userName);
    }

    @Override
    public TbMember getMemberByPhone(String phone) {
        return tbMemberMapper.queryTbMemberByPhone(phone);
    }

    @Override
    public TbMember getMemberByEmail(String email) {
        return tbMemberMapper.queryTbMemberByEmail(email);
    }

    @Override
    public TbMember insertMember(TbMember tbMember) {
        if(getMemberByUserName(tbMember.getUsername()) != null) throw new MallException("用户名已存在");
        if(getMemberByPhone(tbMember.getPhone()) != null) throw new MallException("用户电话已存在");
        if(getMemberByEmail(tbMember.getEmail()) != null) throw new MallException("用户邮箱已存在");
        tbMember.setId(IDUtil.getRandomId());
        tbMember.setState(1);
        tbMember.setCreated(new Date());
        tbMember.setUpdated(new Date());
        tbMember.setPassword(DigestUtils.md5DigestAsHex(tbMember.getPassword().getBytes()));
        if (tbMemberMapper.insertMember(tbMember) != 1) throw new MallException("添加用户失败");
        return tbMember;
    }

    @Override
    public TbMember changePassMember(Long id, TbMemberDto tbMemberDto) {
        TbMember tbMember=tbMemberMapper.queryTbMemberById(id);

        String md5Pass = DigestUtils.md5DigestAsHex(tbMemberDto.getPassword().getBytes());
        tbMember.setPassword(md5Pass);
        tbMember.setUpdated(new Date());

        if (tbMemberMapper.updateMember(tbMember) != 1){
            throw new MallException("修改用户密码失败");
        }
        return queryTbMemberById(id);
    }

    @Override
    public TbMember getMemberByEditUsername(Long id, String username) {
        TbMember tbMember=queryTbMemberById(id);
        TbMember newTbMember=null;
        if(tbMember.getUsername()==null||!tbMember.getUsername().equals(username)){
            newTbMember=getMemberByUserName(username);
        }
        if (newTbMember != null) {
            newTbMember.setPassword("");
        }
        return newTbMember;
    }

    @Override
    public TbMember getMemberByEditPhone(Long id, String phone) {
        TbMember tbMember=queryTbMemberById(id);
        TbMember newTbMember=null;
        if(tbMember.getPhone()==null||!tbMember.getPhone().equals(phone)){
            newTbMember=getMemberByPhone(phone);
        }
        if (newTbMember != null) {
            newTbMember.setPassword("");
        }
        return newTbMember;
    }

    @Override
    public TbMember getMemberByEditEmail(Long id, String email) {
        TbMember tbMember=queryTbMemberById(id);
        TbMember newTbMember=null;
        if(tbMember.getEmail()==null||!tbMember.getEmail().equals(email)){
            newTbMember=getMemberByEmail(email);
        }
        if (newTbMember != null) {
            newTbMember.setPassword("");
        }
        return newTbMember;
    }

    @Override
    public TbMember updateMember(Long id, TbMemberDto memberDto) {
        TbMember tbMember = DtoUtil.MemberDto2Member(memberDto);
        tbMember.setId(id);
        tbMember.setUpdated(new Date());
        TbMember oldMember=queryTbMemberById(id);
        tbMember.setState(oldMember.getState());
        tbMember.setCreated(oldMember.getCreated());
        if(tbMember.getPassword()==null|| tbMember.getPassword().equals("")){
            tbMember.setPassword(oldMember.getPassword());
        }else{
            String md5Pass = DigestUtils.md5DigestAsHex(tbMember.getPassword().getBytes());
            tbMember.setPassword(md5Pass);
        }

        if (tbMemberMapper.updateMember(tbMember) != 1){
            throw new MallException("更新会员信息失败");
        }
        return queryTbMemberById(id);
    }

    @Override
    public void deleteMember(Long id) {
        if(tbMemberMapper.deleteByPrimaryKey(id) != 1){
            throw new MallException("删除会员失败");
        }
    }

}
