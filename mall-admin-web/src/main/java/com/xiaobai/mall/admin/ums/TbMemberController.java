package com.xiaobai.mall.admin.ums;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaobai.mall.constant.MemberState;
import com.xiaobai.mall.dto.TbMemberDto;
import com.xiaobai.mall.ums.entity.TbMember;
import com.xiaobai.mall.ums.service.TbMemberService;
import com.xiaobai.mall.utils.DtoUtil;
import com.xiaobai.mall.utils.ResultUtil;
import com.xiaobai.mall.vo.DataTablesResultVo;
import com.xiaobai.mall.vo.Result;
import org.springframework.web.bind.annotation.*;


/**
 * @author 江湖人称白玉汤
 * @program 毕业设计
 * @description 商城用户 controller
 * @create 2020-03-10 14:40
 **/
@RestController
@RequestMapping("/member")
public class TbMemberController {

    @Reference
    private TbMemberService tbMemberService;
    private static final String[] COLS = {"checkbox","id", "username","gender", "phone", "email", "address", "created", "updated", "state"};

    @GetMapping("/count")
    public DataTablesResultVo getMemberCount() {
        DataTablesResultVo resultVo = new DataTablesResultVo();
        Integer memberCount = tbMemberService.getMemberCount(MemberState.ENABLE) + tbMemberService.getMemberCount(MemberState.UN_ENABLE);
        resultVo.setRecordsTotal(memberCount);
        return resultVo;
    }
    @GetMapping("/count/remove")
    public DataTablesResultVo getRemoveMemberCount() {
        DataTablesResultVo resultVo = new DataTablesResultVo();
        Integer memberCount = tbMemberService.getMemberCount(MemberState.REMOVED);
        resultVo.setRecordsTotal(memberCount);
        return resultVo;
    }

    @RequestMapping("/queryTbMember")
    public TbMember queryTbMemberById(@RequestParam Long id) {
        System.out.println(tbMemberService);
        return tbMemberService.queryTbMemberById(id);
    }

    @GetMapping("/list")
    public DataTablesResultVo getMemberList(int draw, int start, int length, String searchKey,
                                            String minDate, String maxDate,
                                            @RequestParam("search[value]") String search,
                                            @RequestParam("order[0][column]") int orderCol,
                                            @RequestParam("order[0][dir]") String orderDir){

        //默认排序列
        String orderColumn = "created";
        //获取客户端需要排序的列
        orderColumn = COLS[orderCol];

        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        if(!search.isEmpty()){
            searchKey = search;
        }
        DataTablesResultVo result = tbMemberService.getMemberList(draw,start,length,searchKey,minDate,maxDate,orderColumn,orderDir);
        return result;
    }

    @GetMapping(value = "/list/remove")
    public DataTablesResultVo getDelMemberList(int draw, int start, int length, String searchKey,
                                             String minDate, String maxDate,
                                             @RequestParam("search[value]") String search,
                                             @RequestParam("order[0][column]") int orderCol,
                                             @RequestParam("order[0][dir]") String orderDir){
        //默认排序列
        String orderColumn = "created";
        //获取客户端需要排序的列
        orderColumn = COLS[orderCol];
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        if(!search.isEmpty()){
            searchKey = search;
        }
        return tbMemberService.getRemoveMemberList(draw,start,length,searchKey,minDate,maxDate,orderColumn,orderDir);
    }
    @PutMapping("/start/{ids}")
    public Result<TbMember> startMember(@PathVariable("ids") Long[] ids) {
        for (Long id : ids) {
            TbMember tbMember = tbMemberService.updateMemberStatus(id, MemberState.ENABLE);
        }
        return new ResultUtil<TbMember>().setData(null);
    }
    @PutMapping("/stop/{id}")
    public Result<TbMember> stopMember(@PathVariable Long id) {
        TbMember tbMember = tbMemberService.updateMemberStatus(id, MemberState.UN_ENABLE);
        return new ResultUtil<TbMember>().setData(tbMember);
    }
    @PutMapping("/del/{id}")
    public Result<TbMember> delMember(@PathVariable Long id) {
        TbMember tbMember = tbMemberService.updateMemberStatus(id, MemberState.REMOVED);
        return new ResultUtil<TbMember>().setData(tbMember);
    }

    @GetMapping("/username")
    public Boolean checkUserName(@RequestParam("username") String userName) {
        return tbMemberService.getMemberByUserName(userName) == null;
    }
    @GetMapping("/phone")
    public Boolean checkPhone(@RequestParam("phone") String phone) {
        return tbMemberService.getMemberByPhone(phone) == null;
    }
    @GetMapping("/email")
    public Boolean checkEmail(@RequestParam("email") String email) {
        return tbMemberService.getMemberByEmail(email) == null;
    }
    @PostMapping("/add")
    public Result<TbMember> createMember(@ModelAttribute TbMemberDto tbMemberDto) {
        TbMember tbMember = DtoUtil.MemberDto2Member(tbMemberDto);
        return new ResultUtil<TbMember>().setData(tbMemberService.insertMember(tbMember));
    }
    @PostMapping("/changePass/{id}")
    public Result<TbMember> changeMemberPassword(@PathVariable Long id,@ModelAttribute TbMemberDto memberDto){
        return new ResultUtil<TbMember>().setData(tbMemberService.changePassMember(id,memberDto));
    }
    @GetMapping("/edit/{id}/username")
    public Boolean validateEditUsername(@PathVariable Long id,String username){

        if(tbMemberService.getMemberByEditUsername(id,username)!=null){
            return false;
        }
        return true;
    }

    @GetMapping("/edit/{id}/phone")
    public Boolean validateEditPhone(@PathVariable Long id,String phone){

        if(tbMemberService.getMemberByEditPhone(id,phone)!=null){
            return false;
        }
        return true;
    }

    @GetMapping(value = "/edit/{id}/email")
    public Boolean validateEditEmail(@PathVariable Long id,String email){

        if(tbMemberService.getMemberByEditEmail(id,email)!=null){
            return false;
        }
        return true;
    }
    @PostMapping("/update/{id}")
    public Result<TbMember> updateMember(@PathVariable Long id,@ModelAttribute TbMemberDto memberDto){

        TbMember tbMember = tbMemberService.updateMember(id,memberDto);
        return new ResultUtil<TbMember>().setData(tbMember);
    }
    @PutMapping("/remove/{ids}")
    public Result<TbMember> removeMember(@PathVariable Long[] ids){

        for(Long id:ids){
            tbMemberService.updateMemberStatus(id,MemberState.REMOVED);
        }
        return new ResultUtil<TbMember>().setData(null);
    }

    @DeleteMapping("/del/{ids}")
    public Result<TbMember> deleteMember(@PathVariable Long[] ids){

        for(Long id:ids){
            tbMemberService.deleteMember(id);
        }
        return new ResultUtil<TbMember>().setData(null);
    }
}
