package com.songzi.yummy.controller.buy;

import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Address;
import com.songzi.yummy.entity.Level;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.service.AddressService;
import com.songzi.yummy.service.MemberService;
import com.songzi.yummy.util.aliyunoss.AliyunOSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Controller
public class BuyMemberController extends BaseController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private MemberService memberService;

    //转到前台-用户详情页
    @RequestMapping(value = "memberDetails", method = RequestMethod.GET)
    public String goToUserDetail(HttpSession session, Map<String,Object> map){
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        if (memberId != null) {
            logger.info("获取用户信息");
            Member member = memberService.get((long)memberId);
            map.put("member", member);

            logger.info("获取地址");
            ArrayList<Address> addressList = addressService.getAddressByMemId(member.getId());

            Level level = memberService.getLevelByMemId(member.getId());

            map.put("level", level);
            map.put("addressList", addressList);
            return  "buy/memberDetails";
        } else {
            return "redirect:/login";
        }
    }

    //前台-用户详情更新
    @ResponseBody
    @RequestMapping(value="/doUpdate",method=RequestMethod.POST,produces ="application/json;charset=utf-8")
    public String userUpdate(HttpSession session, Map<String,Object> map,
                             @RequestParam(value = "member_name") String member_name  /*用户昵称 */,
                             @RequestParam(value = "member_password") String member_password /* 用户头像*/,
                             @RequestParam(value = "preferredAddress") String preferredAddress/* 用户密码 */
    ) throws ParseException, UnsupportedEncodingException {
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        if (memberId != null) {
            logger.info("获取用户信息");
            Member member = memberService.get((long)memberId);
            map.put("member", member);

            logger.info("创建用户对象");
            if(!member_name.equals(null) && !member_name.equals("")) member.setName(member_name);
            if(!member_password.equals(null) && !member_password.equals("")) member.setPassword(member_password);
            if(!preferredAddress.equals(null) && !preferredAddress.equals("")) member.setPreferredAddress(preferredAddress);
            logger.info("执行修改");
            //System.out.println(member.getName());
            boolean yn = memberService.update(member);
            if (yn){
                logger.info("修改成功!跳转到用户详情页面");
                return "redirect:/memberDetails";
            }
        } else {
            return "redirect:/login";
        }

        throw new RuntimeException();
    }

    @RequestMapping(value="/doDelete",method=RequestMethod.GET,produces ="application/json;charset=utf-8")
    public String doDelete(HttpSession session
    ) throws ParseException, UnsupportedEncodingException {
        JSONObject object = new JSONObject();
        Object o = session.getAttribute("memberId");
        if (o != null) {
            Member member = memberService.get((long)o);
            member.setState(0);
            memberService.update(member);
            object.put("success", true);
            session.removeAttribute("memberId");
            session.invalidate();
            logger.info("登录信息已清除，返回用户登录页");
        }
        return "redirect:/login";
    }
}
