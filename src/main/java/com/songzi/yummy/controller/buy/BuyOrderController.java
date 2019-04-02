package com.songzi.yummy.controller.buy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.bean.Cart;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.*;
import com.songzi.yummy.service.*;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BuyOrderController extends BaseController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FoodorderService foodorderService;
    @Autowired
    private FoodorderitemService foodorderitemService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StrategyService strategyService;

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map,
                           @RequestParam(required = false) Byte status) {
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        Member member;
        if (memberId != null) {
            logger.info("获取用户信息");
            member = memberService.get((long) memberId);
            map.put("member", member);
        } else {
            return "redirect:/login";
        }

        logger.info("根据用户ID:{}获取订单列表", memberId);

        List<Foodorder> foodOrderListByMember = foodorderService.getOrderByMember(member);

        List<Foodorder> foodOrderListStatus = new ArrayList<Foodorder>();

        if(status == null) {
            map.put("foodOrderList",  foodOrderListByMember);
        }
        else {
            for (Foodorder foodorder : foodOrderListByMember) {
                if (foodorder.getStatus() == status) {
                    foodOrderListStatus.add(foodorder);
                }
            }
            map.put("foodOrderList",  foodOrderListStatus);
        }


        logger.info("获取产品分类列表信息");
        List<Category> categoryList = categoryService.getCategoryForHomePage();

        map.put("categoryList", categoryList);
        map.put("status", status);

        logger.info("转到订单列表页");
        return "buy/orderListPage";
    }

    //转到购物车订单建立页
    @RequestMapping(value = "/order/create/byCart", method = RequestMethod.GET)
    public String goToOrderConfirmPageByCart(Map<String, Object> map,
                                             HttpSession session, HttpServletRequest request,
                                             @RequestParam(required = false) long[] order_item_list) throws UnsupportedEncodingException {
        logger.info("转到购物车订单建立页");
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        Member member;
        if (memberId != null) {
            logger.info("获取用户信息");
            member = memberService.get((long) memberId);
            map.put("member", member);
        } else {
            return "redirect:/login";
        }
        if (order_item_list == null || order_item_list.length == 0) {
            logger.warn("用户订单项数组不存在，回到购物车页");
            return "redirect:/cart";
        }
        logger.info("通过订单项ID数组获取订单信息");
        List<Foodorderitem> orderItemList = new ArrayList<>(order_item_list.length);
        for (long orderItem_id : order_item_list) {
            orderItemList.add(foodorderitemService.get(orderItem_id));
        }
        logger.info("检查订单项是否合理");
        if (orderItemList.size() == 0) {
            logger.warn("用户订单项获取失败，回到购物车页");
            return "redirect:/cart";
        }

        logger.info("验证通过，获取订单项的产品信息");
        double orderTotalPrice = 0.0;
        for (Foodorderitem orderItem : orderItemList) {
            orderTotalPrice += orderItem.getPrice() * orderItem.getNumber();
        }
        logger.info("订单总额为" + orderTotalPrice);
        String receiver = null;
        String phonenumber = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                switch (cookieName) {
                    case "receiver":
                        receiver = URLDecoder.decode(cookieValue, "UTF-8");
                        break;
                    case "phonenumber":
                        phonenumber = URLDecoder.decode(cookieValue, "UTF-8");
                        break;
                }
            }
        }

        Foodorder foodorder = foodorderService.getOrderByOrderitem(orderItemList.get(0));
        List<Address> addressList = addressService.getAddressByMemId((long)memberId);
        Collection<Strategy1> strategy1List = foodorder.getRestaurantByResId().getStrategy1sById();

        double discount = 0;
        if(strategy1List.size() == 0) discount = 0;
        else {
            for (Strategy1 strategy : strategy1List) {
                if (strategy.getFull() < orderTotalPrice && strategy.getDiscount() > discount) {
                    discount = strategy.getDiscount();
                }
            }
        }
        logger.info("更新总金额");
        foodorder.setTotalPrice(orderTotalPrice - discount);
        foodorderService.update(foodorder);

        map.put("orderItemList", orderItemList);
        map.put("orderTotalPrice", orderTotalPrice);
        map.put("discount", discount);
        map.put("addressList", addressList);
        map.put("receiver", receiver);
        map.put("phonenumber", phonenumber);

        logger.info("转到订单建立页");
        return "buy/foodBuyPage";
    }

    //转到订单支付页
    @RequestMapping(value = "/order/pay/{order_id}", method = RequestMethod.GET)
    public String goToOrderPayPage(
            Map<String, Object> map,
            HttpSession session,
            @PathVariable("order_id") String order_id) {
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        Member member;
        if (memberId != null) {
            logger.info("获取用户信息");
            member = memberService.get((long) memberId);
            map.put("member", member);
        } else {
            return "redirect:/login";
        }

        logger.info("------验证订单信息------");
        logger.info("查询订单是否存在");
        Foodorder foodorder = foodorderService.get(Long.valueOf(order_id));
        if (foodorder == null) {
            logger.warn("订单不存在，返回订单列表页");
            return "redirect:/order";
        }
        logger.info("验证订单状态");
        if (foodorder.getStatus() != 2) {
            logger.warn("订单状态不正确，返回订单列表页");
            return "redirect:/order";
        }
        logger.info("验证用户与订单是否一致");
        if (foodorder.getMemId() != (long) memberId) {
            logger.warn("用户与订单信息不一致，返回订单列表页");
            return "redirect:/order";
        }

        double orderTotalPrice = 0.0;
        List<Foodorderitem> foodorderitems = foodorderitemService.getOrderitemListByOrderId(Long.valueOf(order_id));
        if (foodorderitems.size() >= 1) {
            for (Foodorderitem f : foodorderitems) {
                orderTotalPrice += f.getNumber() * f.getPrice();
            }
            logger.info("订单总金额为：{}元", orderTotalPrice);
        }
        Account account = member.getAccountByAccountId();

        map.put("account", account);
        map.put("foodorder", foodorder);
        map.put("orderTotalPrice", orderTotalPrice);
        logger.info("转到订单支付页");
        return "buy/foodPayPage";

    }

    //更新订单信息为已支付，待发货-ajax
    @ResponseBody
    @RequestMapping(value = "/order/pay/{order_id}", method = RequestMethod.PUT)
    public String orderPay(
            HttpSession session,
            @PathVariable("order_id") String order_id
    ) {
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        if (memberId == null) {
            object.put("success", false);
            object.put("url", "/login");
            return object.toJSONString();
        }

        logger.info("------验证订单信息------");
        Member member = memberService.get((long)memberId);
        Foodorder foodorder = foodorderService.get(Long.valueOf(order_id));
        long accountId = member.getAccountId();
        Account account = accountService.getById(accountId);
        if (foodorder == null) {
            logger.warn("订单不存在，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order");
            return object.toJSONString();
        }
        logger.info("验证订单状态");
        if (foodorder.getStatus() != 2) {
            logger.warn("订单状态不正确，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order");
            return object.toJSONString();
        }

        logger.info("更新订单信息");
        foodorder.setStatus((byte)3);
        foodorder.setPayDate(new Timestamp(System.currentTimeMillis()));

        double needTime = addressService.getNeedTime(foodorder.getRestaurantByResId().getCoordinate(),
                foodorder.getAddressByAddressId().getCoordinate());
        foodorder.setNeedTime(/**时间*/needTime);

        logger.info("更新账单信息");
        double balance = account.getBalance();
        double totalPrice = 0;
        for(Foodorderitem foodorderitem : foodorder.getFoodorderitemsById()){
            totalPrice += foodorderitem.getPrice() * foodorderitem.getNumber();
        }
        account.setBalance(balance - totalPrice);
        logger.info("新增账单明细");
        /**Integer type, Double money, Member memberByMemId, Restaurant restaurantByResId*/
        Accountdetail accountdetail = new Accountdetail(1, totalPrice, member, foodorder.getRestaurantByResId());

        Foodorder foodorder1 = foodorderService.update(foodorder);
        Accountdetail accountdetail1 = accountService.updateDetail(accountdetail);
        boolean account1 = accountService.update(account);

        if (!foodorder1.equals(null) && account1 && !accountdetail1.equals(null)) {
            object.put("success", true);
            object.put("url", "/order");
        } else {
            object.put("success", false);
            object.put("url", "/order");
        }
        return object.toJSONString();
    }


    //转到订单支付成功页
    @RequestMapping(value = "/order/pay/success/{order_id}", method = RequestMethod.GET)
    public String goToOrderPaySuccessPage(
            Map<String, Object> map,
            HttpSession session,
            @PathVariable("order_id") String order_id) {
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        Member member;
        if (memberId != null) {
            logger.info("获取用户信息");
            member = memberService.get((long) memberId);
            map.put("member", member);
        } else {
            return "redirect:/login";
        }

        logger.info("------验证订单信息------");
        logger.info("查询订单是否存在");
        Foodorder foodorder = foodorderService.get(Long.valueOf(order_id));
        if (foodorder == null) {
            logger.warn("订单不存在，返回订单列表页");
            return "redirect:/order";
        }
        logger.info("验证订单状态");
        if (foodorder.getStatus() != 3) {
            logger.warn("订单状态不正确，返回订单列表页");
            return "redirect:/order";
        }
        logger.info("验证用户与订单是否一致");
        if (foodorder.getMemId() != memberId) {
            logger.warn("用户与订单信息不一致，返回订单列表页");
            return "redirect:/order";
        }

        double orderTotalPrice = 0.0;
        List<Foodorderitem> foodorderitems = foodorderitemService.getOrderitemListByOrderId(Long.valueOf(order_id));
        if (foodorderitems.size() == 1) {
            for (Foodorderitem f : foodorderitems) {
                orderTotalPrice += f.getNumber() * f.getPrice();
            }
            logger.info("订单总金额为：{}元", orderTotalPrice);
        }

        logger.info("获取订单详情-地址信息");
        //Address address = addressService.;

        map.put("foodorder", foodorder);
        map.put("orderTotalPrice", orderTotalPrice);

        logger.info("转到订单支付成功页");
        return "buy/productPaySuccessPage";
    }

    //转到订单确认页
    @ResponseBody
    @RequestMapping(value = "/order/confirm/{order_id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String goToOrderConfirmPage(
            Map<String, Object> map,
            HttpSession session,
            @PathVariable("order_id") String order_id) {
        logger.info("检查用户是否登录");
        JSONObject object = new JSONObject();
        Object memberId = checkMember(session);
        Member member;
        if (memberId != null) {
            logger.info("获取用户信息");
            member = memberService.get((long) memberId);
            map.put("member", member);
        } else {
            return "redirect:/login";
        }

        logger.info("------验证订单信息------");
        logger.info("查询订单是否存在");
        Foodorder foodorder = foodorderService.get(Long.valueOf(order_id));
        logger.info("验证订单状态");
        //注意订单状态信息
        if (foodorder.getStatus() != 3) {
            logger.warn("订单状态不正确，返回订单列表页");
            return "redirect:/order";
        }

        double orderTotalPrice = 0.0;
        List<Foodorderitem> foodorderitems = foodorderitemService.getOrderitemListByOrderId(Long.valueOf(order_id));
        if (foodorderitems.size() == 1) {
            for (Foodorderitem f : foodorderitems) {
                orderTotalPrice += f.getNumber() * f.getPrice();
            }
        }
        logger.info("订单总金额为：{}元", orderTotalPrice);

        logger.info("商家收款金额为：{}元", orderTotalPrice);
        Account account = foodorder.getRestaurantByResId().getAccountByAccountId();
        account.setBalance(account.getBalance() + orderTotalPrice);
        foodorder.setStatus((byte)4);
        foodorder.setConfirmDate(new Timestamp(System.currentTimeMillis()));

        Foodorder foodorder1 = foodorderService.update(foodorder);
        boolean yn = accountService.update(account);
        if(yn && !foodorder1.equals(null)){
            object.put("success", true);
        } else{
            object.put("success", false);
        }
        logger.info("订单确认，餐厅收钱");
        return object.toJSONString();
    }

    //转到订单完成页
    @RequestMapping(value = "/order/success/{order_id}", method = RequestMethod.GET)
    public String goToOrderSuccessPage(Map<String, Object> map, HttpSession session,
                                       @PathVariable("order_id") String order_id) {
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        Member member;
        if (memberId != null) {
            logger.info("获取用户信息");
            member = memberService.get((long) memberId);
            map.put("member", member);
        } else {
            return "redirect:/login";
        }

        logger.info("------验证订单信息------");
        logger.info("查询订单是否存在");
        Foodorder foodorder = foodorderService.get(Long.valueOf(order_id));
        if (foodorder == null) {
            logger.warn("订单不存在，返回订单列表页");
            return "redirect:/order";
        }
        logger.info("验证订单状态");
        if (foodorder.getStatus() != 3) {
            logger.warn("订单状态不正确，返回订单列表页");
            return "redirect:/order";
        }
        logger.info("验证用户与订单是否一致");

        if (foodorder.getMemId() != (long)memberId) {
            logger.warn("用户与订单信息不一致，返回订单列表页");
            return "redirect:/order";
        }
        logger.info("获取订单中订单项数量");
        List<Foodorderitem> foodorderitemList = foodorderitemService.getOrderitemListByOrderId(Long.valueOf(order_id));
        Food food = null;
        if (foodorderitemList.size() == 1) {
            logger.info("获取订单中的唯一订单项");
            Foodorderitem foodorderitem = foodorderitemList.get(0);
            if (foodorderitem != null) {
                logger.info("获取订单项评论数量");
                food = foodorderitem.getFoodByFoodId();
                /*count = reviewService.getTotalByOrderItemId(productOrderItem.getProductOrderItem_id());
                if (count == 0) {
                    logger.info("获取订单项产品信息");
                    product = productService.get(productOrderItem.getProductOrderItem_product().getProduct_id());
                    if (product != null) {
                        product.setSingleProductImageList(productImageService.getList(product.getProduct_id(), (byte) 0, new PageUtil(0, 1)));
                    }
                }*/
            }
            map.put("foodorderitem", foodorderitem);
        }

        map.put("food", food);

        logger.info("转到订单完成页");
        return "buy/orderSuccessPage";
    }

    //更新订单信息为交易成功-ajax
    @ResponseBody
    @RequestMapping(value = "/order/success/{order_id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String orderSuccess(HttpSession session, @PathVariable("order_id") String order_id) {
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);

        if (memberId == null) {
            object.put("success", false);
            object.put("url", "/login");
            return object.toJSONString();
        }
        logger.info("------验证订单信息------");
        logger.info("查询订单是否存在");
        Foodorder foodorder = foodorderService.get(Long.valueOf(order_id));
        if (foodorder == null) {
            logger.warn("订单不存在，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order");
            return object.toJSONString();
        }
        logger.info("验证订单状态");
        if (foodorder.getStatus() != 4) {
            logger.warn("订单状态不正确，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order");
            return object.toJSONString();
        }
        logger.info("验证用户与订单是否一致");
        if (foodorder.getMemId() != (long)memberId) {
            logger.warn("用户与订单信息不一致，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order");
            return object.toJSONString();
        }
        logger.info("更新订单信息");
        foodorder.setStatus((byte)3);

        Foodorder yn = foodorderService.update(foodorder);

        if (!yn.equals(null)) {
            object.put("success", true);
        } else {
            object.put("success", false);
        }
        return object.toJSONString();
    }

    //更新订单信息为交易关闭-ajax
    @ResponseBody
    @RequestMapping(value = "/order/close/{order_id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String orderClose(HttpSession session, @PathVariable("order_id") String order_id) {
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        if (memberId == null) {
            object.put("success", false);
            object.put("url", "/login");
            return object.toJSONString();
        }
        logger.info("------验证订单信息------");
        logger.info("查询订单是否存在");
        Foodorder foodorder = foodorderService.get(Long.valueOf(order_id));
        if (foodorder == null) {
            logger.warn("订单不存在，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order");
            return object.toJSONString();
        }
        logger.info("验证用户与订单是否一致");
        if (foodorder.getMemId() != (long)memberId) {
            logger.warn("用户与订单信息不一致，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order");
            return object.toJSONString();
        }
        logger.info("更新订单信息");
        foodorder.setStatus((byte)0);

        Foodorder yn = foodorderService.update(foodorder);
        if ( !yn.equals(null) ) {
            object.put("success", true);
        } else {
            object.put("success", false);
        }
        return object.toJSONString();
    }

    //更新购物车订单项数量-ajax
    @ResponseBody
    @RequestMapping(value = "/orderItem", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String updateOrderItem(HttpSession session,
                                  Map<String, Object> map,
                                  HttpServletResponse response,
                                  @RequestParam String orderItemMap)    {
        JSONObject object = new JSONObject();

        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        if (memberId == null) {
            object.put("success", false);
            return object.toJSONString();
        }

        JSONObject orderItemString = JSON.parseObject(orderItemMap);
        Set<String> orderItemIDSet = orderItemString.keySet();
        //System.out.println(orderItemIDSet.size());

        if (orderItemIDSet.size() > 0) {
            logger.info("更新产品订单项数量");
            for (String key : orderItemIDSet) {
                Foodorderitem foodorderitem = foodorderitemService.get(Long.valueOf(key));
                if (foodorderitem == null || foodorderitem.getMemId()!=(long)memberId) {
                    logger.info("订单项为空或用户状态不一致！");
                    object.put("success", false);
                    object.put("msg", "失败");
                    return object.toJSONString();
                }
            }
            Object[] orderItemIDArray = orderItemIDSet.toArray();
            object.put("success", true);
            object.put("msg", "成功");
            //System.out.println(object.get("success"));
            object.put("orderItemIDArray", orderItemIDArray);
            return object.toJSONString();
        } else {
            logger.info("无订单项可以处理");
            object.put("success", false);
            object.put("msg", "失败");
            return object.toJSONString();
        }
    }

    //转到购物车页
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String goToCartPage(Map<String, Object> map, HttpSession session) {
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        Member member;
        if (memberId != null) {
            logger.info("获取用户信息");
            member = memberService.get((long) memberId);
            map.put("member", member);
        } else {
            return "redirect:/login";
        }
        logger.info("获取用户购物车信息");
        List<Foodorder> foodorderList = foodorderService.getOrderByStatus((byte)1);
        if(foodorderList.size() == 0) return "buy/foodBuyCartPage";
        Foodorder foodorder = foodorderList.get(0);
        List<Foodorderitem> foodorderitemList = foodorderitemService.getOrderitemListByOrderId(foodorder.getId());

        List<Category> categoryList = categoryService.getCategoryForHomePage();
        map.put("categoryList", categoryList);
        map.put("foodorderitemList", foodorderitemList);
        map.put("orderItemTotal", foodorderitemList.size());
        //System.out.println(foodorderitemList.size());

        //System.out.println(foodorderitemList.size());
        logger.info("转到购物车页");
        return "buy/foodBuyCartPage";
    }

    //删除购物车中的商品
    @ResponseBody
    @RequestMapping(value = "/cart/deleteItem", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteItem(
            HttpSession session,
            @RequestParam(value = "foodorderitemId") long foodorderitemId  /*单项订单的id */
    ) throws ParseException                                                                                                                                                  {
        logger.info("删除单个订单");
        JSONObject object = new JSONObject();
        long [] ids = new long[1];
        ids[0] = foodorderitemId;
        Foodorderitem foodorderitem = foodorderitemService.get(foodorderitemId);
        Food food = foodorderitem.getFoodByFoodId();
        food.setAmount(food.getAmount()+foodorderitem.getNumber());

        boolean yn = foodorderitemService.deleteList(ids);
        foodService.update(food);
        object.put("success", yn);
        return object.toJSONString();
    }

    //支付订单多订单项-ajax
    @ResponseBody
    @RequestMapping(value = "/order/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String createOrderByList(HttpSession session, Map<String, Object> map, HttpServletResponse response,
                                    @RequestParam String foodorder_address,
                                    @RequestParam String foodorder_receiver,
                                    @RequestParam String foodorder_phonenumber,
                                    @RequestParam String foodorderitem_id
    ) throws UnsupportedEncodingException {
        logger.info("支付订单");

        JSONObject object = new JSONObject();

        Foodorder foodorder;
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        if (memberId == null) {
            object.put("success", false);
            object.put("url", "/login");
            return object.toJSONString();
        }

        List<Foodorderitem> foodOrderItemList = new ArrayList<Foodorderitem>();

        Foodorderitem foodorderitem = foodorderitemService.get(Long.valueOf(foodorderitem_id));
        foodorder = foodorderService.getOrderByOrderitem(foodorderitem);
        Address address = addressService.getById(Long.valueOf(foodorder_address));

        foodorder.setStatus((byte)2);
        foodorder.setAddressByAddressId(address);
        foodorder.setReceiver(foodorder_receiver);
        foodorder.setPhonenumber(foodorder_phonenumber);

        logger.info("将相关信息存入Cookie中");

        Cookie cookie1 = new Cookie("receiver", URLEncoder.encode(foodorder_receiver, "UTF-8"));
        Cookie cookie2 = new Cookie("phonenumber", URLEncoder.encode(foodorder_phonenumber, "UTF-8"));
        //设置过期时间为一年
        int maxAge = 60 * 60 * 24 * 365;
        cookie1.setMaxAge(maxAge);
        cookie2.setMaxAge(maxAge);
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        foodorderService.update(foodorder);

        object.put("success", true);
        object.put("url", "/order/pay/" + foodorder.getId());
        return object.toJSONString();
    }

    //创建订单项-购物车-ajax
    @ResponseBody
    //orderItem/create/${requestScope.food.id}
    @RequestMapping(value = "/orderItem/create/{food_id}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String createOrderItem(@PathVariable("food_id") long food_id,
                                  @RequestParam(required = false, defaultValue = "1") int food_number,
                                  HttpSession session,
                                  HttpServletRequest request) {
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        if (memberId == null) {
            object.put("url", "/login");
            object.put("success", false);
            return object.toJSONString();
        }
        Member member = memberService.get((long)memberId);
        List<Address> addressList = addressService.getAddressByMemId((long)memberId);
        logger.info("通过产品ID获取产品信息：{}", food_id);
        Food food = foodService.get(food_id);

        //1 表示正在购物车中的order
        List<Foodorder> foodorderList = foodorderService.getOrderByStatus((byte)1);

        logger.info("检查用户的购物车项");
        Restaurant restaurant = food.getRestaurantByResId();
        Foodorder foodorder = new Foodorder();

        List<Foodorderitem> foodorderitemList = new ArrayList<Foodorderitem>();

        if(foodorderList.size() == 0){
            foodorder = new Foodorder((byte)1, addressList.get(0), member, restaurant, foodorderitemList);
        }else {
            foodorder = foodorderList.get(0);
            foodorderitemList = foodorderitemService.getOrderitemListByOrderId(foodorder.getId());
            for (Foodorderitem foodorderitem : foodorderitemList) {
                if (foodorderitem.getFoodId() == food_id) {
                    logger.info("找到已有的产品，进行数量追加");
                    int number = foodorderitem.getNumber();
                    number += 1;
                    foodorderitem.setNumber(number);

                    Foodorderitem yn = foodorderitemService.update(foodorderitem);
                    if (!yn.equals(null)) {
                        object.put("success", true);
                    } else {
                        object.put("success", false);
                    }
                }
            }
        }

        if(object.get("success") == null){
            logger.info("现有订单中并不存在这个商品，新加订单单项");
            //Member member, Food food, int number, Foodorder foodorder
            Foodorderitem foodorderitem1 = new Foodorderitem(memberService.get((long)memberId),
                    food, food_number, foodorder);
            List<Foodorderitem> foodorderitemArrayList = foodorder.getFoodorderitemsById();
            foodorderitemArrayList.add(foodorderitem1);
            foodorder.setFoodorderitemsById(foodorderitemArrayList);

            Foodorder foodorder1 = foodorderService.update(foodorder);
            Foodorderitem yn = foodorderitemService.update(foodorderitem1);
            if (!foodorder1.equals(null) && !yn.equals(null)) {
                object.put("success", true);
            } else {
                object.put("success", false);
            }
        }
        food.setAmount(food.getAmount()-food_number);
        foodService.update(food);//修改数量
        System.out.println(food.getAmount());

        foodorderService.update(foodorder);
        session.setAttribute("foodorder_id", foodorder.getId());

        logger.info("封装订单项对象");
        return object.toJSONString();
    }

}
