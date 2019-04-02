package com.songzi.yummy.controller.manage;

import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Food;
import com.songzi.yummy.entity.Foodorderitem;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerMemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    //转到后台管理-用户页-ajax
    @RequestMapping(value = "manage/member", method = RequestMethod.GET)
    public String goUserManagePage(HttpSession session, Map<String, Object> map){
        logger.info("检查管理员权限");
        Object managerId = checkManage(session);
        if(managerId == null){
            return "manage/include/loginMessage";
        }

        logger.info("获取全部用户信息");
        List<Member> memberList = memberService.getAllMembers();
        map.put("memberList", memberList);

        logger.info("获取用户总数量");
        int memberCount = memberList.size();
        map.put("memberCount", memberCount);

        logger.info("转到后台管理-用户页-ajax方式");
        return "manage/memberManagePage";
    }
}
