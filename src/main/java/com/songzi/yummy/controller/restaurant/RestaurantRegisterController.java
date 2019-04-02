package com.songzi.yummy.controller.restaurant;

import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.bean.ReturnContant;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Account;
import com.songzi.yummy.entity.Category;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.entity.Restaurant;
import com.songzi.yummy.service.AccountService;
import com.songzi.yummy.service.CategoryService;
import com.songzi.yummy.service.MemberService;
import com.songzi.yummy.service.RestaurantService;
import com.songzi.yummy.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**注册申请
 编码：7位识别码，系统自动分配，用于登录
 修改注册信息
 餐厅基本信息
 地点、类型等
 需Yummy!经理审批
 发布信息（未来一个时间段）
 按照日期、类型发布
 单品：价格， 数量；
 以及套餐，优惠等
 */
@Controller
public class RestaurantRegisterController extends BaseController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "restaurant/register", method = RequestMethod.GET)
    public String goToPage(Map<String,Object> map, HttpSession session) {
        logger.info("转到前台-用户注册页");
        List<Category> categoryList = categoryService.getCategoryForHomePage();
        map.put("categoryList",categoryList);

        return "restaurant/restaurantRegisterPage";
    }

    //跳转至注册的隐私条款
    @RequestMapping("/restaurant/toCopyRight")
    public String toCopyRight(){
        return "buy/member_copyright";
    }

    @ResponseBody
    @RequestMapping(value = "/restaurant/register/createAccount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String createAccount(
            HttpSession session
    ) throws ParseException {
        logger.info("创建Yummy!账户");
        JSONObject object = new JSONObject();
        if(session.getAttribute("account") == null){
            Account account = accountService.create();
            session.setAttribute("account", account);
            object.put("success", true);
            object.put("msg", "创建账户成功");
        }else{
            object.put("success", false);
            object.put("msg", "请勿重复创建");
        }
        return object.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/restaurant/register/doRegister", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String register(
            HttpSession session,
            @RequestParam(value = "restaurant_type") String restaurant_type  /*餐厅类型 */,
            @RequestParam(value = "restaurant_category") String restaurant_category  /*餐厅分类 */,
            @RequestParam(value = "restaurant_password") String restaurant_password  /*餐厅登录密码*/,
            @RequestParam(value = "restaurant_name") String restaurant_name /*餐厅名称*/,
            @RequestParam(value = "restaurant_address") String restaurant_address /*餐厅本地地址*/,
            @RequestParam(value = "restaurant_coordinate") String restaurant_coordinate /**餐厅地理地址*/,
            @RequestParam(value = "restaurant_account") String account_id/**yummy！账户*/
    ) throws ParseException {

        logger.info("餐厅注册");
        JSONObject object = new JSONObject();
        //String name,String email, String password

        StringBuilder sb = new StringBuilder();
        String[] beforeShuffle= new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }

        String codenumber = sb.substring(0,7).toString();

        long categoryid = Long.valueOf(restaurant_category);
        Category category1 = categoryService.get(categoryid);

        int type = Integer.valueOf(restaurant_type);
        Account account = accountService.getById(Long.valueOf(account_id));

        Restaurant restaurant = new Restaurant(restaurant_password, type, restaurant_address,
                restaurant_coordinate.replace(" ", ","), codenumber, restaurant_name, categoryid, category1, account);
        if(restaurantService.update(restaurant)){
            object.put("success", true);
            object.put("msg", "注册成功");
            object.put("restaurant", restaurant);
            session.removeAttribute("account");
            session.setAttribute("restaurant", restaurant);

        }else{
            object.put("success", false);
            object.put("msg", "注册失败，验证码不匹配！");
        }
        return object.toJSONString();
    }

    @RequestMapping(value = "/restaurant/register/result")
    public String toResult(){
        return "restaurant/registerResultPage";
    }
}
