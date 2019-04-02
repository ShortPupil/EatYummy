package com.songzi.yummy.controller.buy;

import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class BuyLoginController extends BaseController {
    @Autowired
    private MemberService memberService;

    //转到前台-登录页
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("转到前台登录页");
        return "buy/loginPage";
    }

    //登陆验证-ajax
    @ResponseBody
    @RequestMapping(value = "login/doLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String checkLogin(HttpSession session, @RequestParam String email, @RequestParam String password) {
        logger.info("用户验证登录");

        Member member = memberService.login(email, password);
        JSONObject jsonObject = new JSONObject();
        if (member.equals(null)) {
            logger.info("登录验证失败");
            jsonObject.put("success", false);
        } else {
            logger.info("登录验证成功,用户ID传入会话");
            session.setAttribute("memberId", member.getId());
            jsonObject.put("success", true);
        }
        return jsonObject.toJSONString();
    }

    //退出当前账号
    @RequestMapping(value = "login/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        Object o = session.getAttribute("memberId");
        if (o != null) {
            session.removeAttribute("memberId");
            session.invalidate();
            logger.info("登录信息已清除，返回用户登录页");
        }
        return "redirect:/login";
    }
}
