package com.songzi.yummy.controller.buy;

import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.bean.ReturnContant;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Map;

@Controller
public class BuyRegisterController extends BaseController {
    @Autowired
    private MemberService memberService;
    @Resource
    private ReturnContant returnContant;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String goToPage(Map<String,Object> map) {

        logger.info("转到前台-用户注册页");
        return "buy/registerPage";
    }

    //跳转至注册的隐私条款
    @RequestMapping("/toCopyRight")
    public String toCopyRight(){
        return "buy/member_copyright";
    }

    @ResponseBody
    @RequestMapping(value = "register/sendEmail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String send(
            HttpSession session,
            @RequestParam(value = "member_email") String member_email  /*用户邮箱 */
    ) throws ParseException {
        logger.info("发送邮箱");
        JSONObject object = new JSONObject();
        String code = memberService.sendEmail(member_email);
        object.put("code", code);
        session.setAttribute("code", code);
        System.out.println(code);
        return object.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/register/doRegister", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String register(
            HttpSession session,
            @RequestParam(value = "member_email") String member_email  /*用户邮箱 */,
            @RequestParam(value = "member_name") String member_name  /*用户名 */,
            @RequestParam(value = "member_password") String member_password  /*用户密码*/,
            @RequestParam(value = "member_validateCode") String member_validateCode /*用户验证码获取*/
    ) throws ParseException {

        logger.info("用户注册");
        JSONObject object = new JSONObject();
        //String name,String email, String password
        System.out.println("ok");
        String code = (String) session.getAttribute("code");
        System.out.println(code);

        Member member = memberService.getMemberByEmail(member_email);
        if(member != null){
            object.put("success", false);
            object.put("msg", "该邮箱已经被注册！");
        }
        else if(code.equals(member_validateCode)){
            //String name, String password, String email, String validateCode
            member = memberService.processRegister(member_name, member_password,  member_email,member_validateCode);

            object.put("success", true);
            object.put("msg", "注册成功");
        }else{
            object.put("success", false);
            object.put("msg", "注册失败，验证码不匹配！");
        }
        return object.toJSONString();
    }
}
