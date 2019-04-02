package com.songzi.yummy.controller.restaurant;

import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.*;
import com.songzi.yummy.service.FoodService;
import com.songzi.yummy.service.FoodorderService;
import com.songzi.yummy.service.RestaurantService;
import com.songzi.yummy.service.StrategyService;
import net.bytebuddy.asm.Advice;
import org.codehaus.plexus.classworlds.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@Controller
public class RestaurantHomeController extends BaseController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodorderService foodorderService;
    @Autowired
    private StrategyService strategyService;

    //转到后台管理-主页
    @RequestMapping(value = "restaurant", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) throws ParseException {
        logger.info("检查餐厅权限");
        if(session.getAttribute("restaurantId") == null ||session.getAttribute("restaurantId").equals(null)){
            logger.info("错误");
            return "redirect:/restaurant/login";
        }
        Long restaurantId = (Long) checkRestaurant(session);
        Restaurant restaurant = restaurantService.getById(restaurantId);

        List<Food> foodList = foodService.getFoodByRestaurant(restaurantId);
        List<Foodorder> foodorderList = foodorderService.getOrderByResId(restaurantId);
        Collection<Strategy1> strategy1List = restaurant.getStrategy1sById();

        map.put("restaurant", restaurant);
        map.put("foodList", foodList);
        map.put("foodorderList", foodorderList);
        map.put("strategy1List", strategy1List);
        return "restaurant/restaurantHomePage";
    }

    @ResponseBody
    @RequestMapping(value = "/restaurant/doUpdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String doUpdate(
            HttpSession session,
            @RequestParam(value = "food_id") String food_id  /*餐品id */,
            @RequestParam(value = "food_name") String food_name  /*餐品名称 */,
            @RequestParam(value = "food_price") String food_price  /*餐品价格*/,
            @RequestParam(value = "food_amount") String food_amount /*餐品总量*/
    ) throws ParseException {
        logger.info("餐厅更改餐品信息");
        JSONObject object = new JSONObject();
        if(food_id.equals(null) || food_id.equals("")){
            object.put("success", false);
            object.put("msg", "不存在这个餐品");
            return object.toJSONString();
        }
        Food food = foodService.get(Long.valueOf(food_id));
        if(food_name != null && !food_name.equals("")) food.setName(food_name);
        if(food_price != null && !food_price.equals("")) food.setPrice(Double.valueOf(food_price));
        if(food_amount != null && !food_amount.equals("")) food.setAmount(Long.valueOf(food_amount));
        if (foodService.update(food)){
            object.put("success", true);
            object.put("msg", "更新成功");
        }else{
            object.put("success", false);
            object.put("msg", "更新成功");
        }
        return object.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/restaurant/doDelete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String doUpdate(
            HttpSession session,
            @RequestParam(value = "food_id") String food_id  /*餐品id */
    )throws ParseException {
        logger.info("餐厅删除餐品");
        JSONObject object = new JSONObject();
        if(food_id.equals("")){
            object.put("success", false);
            object.put("msg", "不存在这个餐品");
            return object.toJSONString();
        }

        Food food = foodService.get(Long.valueOf(food_id));
        food.setType(0);
        if (foodService.update(food)){
            object.put("success", true);
            object.put("msg", "删除成功");
        }else{
            object.put("success", false);
            object.put("msg", "删除成功");
        }
        return object.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/restaurant/doAdd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String doAdd(
            HttpSession session,
            @RequestParam(value = "food_name") String food_name,
            @RequestParam(value = "food_price") String food_price,
            @RequestParam(value = "food_amount") String food_amount,
            @RequestParam(value = "food_start_time") String food_start_time,
            @RequestParam(value = "food_end_time") String food_end_time,
            @RequestParam(value = "food_discription") String food_discription
    )throws ParseException {
        logger.info("餐厅新增餐品");
        JSONObject object = new JSONObject();

        //logger.info("图片信息为" + food_picture);
        long res_id = (Long) session.getAttribute("restaurantId");
        Restaurant restaurant = restaurantService.getById(res_id);
        double price = Double.valueOf(food_price);
        long amount = Long.valueOf(food_amount);

        Timestamp start_time = Timestamp.valueOf(food_start_time.replace("T", " ") + ":00");
        Timestamp end_time = Timestamp.valueOf(food_end_time.replace("T", " ") + ":00");

        Food food = new Food(res_id, start_time, end_time,
                food_name, ""/*未完成上传方法*/, amount,
                price, food_discription, restaurant);
        if(foodService.update(food)){
            object.put("success", true);
            object.put("msg", "新增成功");
        } else {
            object.put("success", false);
            object.put("msg", "新增失败");
        }
        return object.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/restaurant/doUpdateStrategy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String doUpdateStrategy(
            HttpSession session,
            @RequestParam(value = "strategy_id") String strategy_id  /*餐品id */,
            @RequestParam(value = "strategy_full") String strategy_full  /*满多少 */,
            @RequestParam(value = "strategy_discount") String strategy_discount  /*减多少 */
    ) throws ParseException {
        logger.info("餐厅更改促销策略");
        JSONObject object = new JSONObject();
        if(strategy_id.equals(null) || strategy_id.equals("")){
            object.put("success", false);
            object.put("msg", "不存在这个策略");
            return object.toJSONString();
        }
        Strategy1 strategy1 = strategyService.getStrategy1ById(Long.valueOf(strategy_id));
        if(strategy_full != null && !strategy_full.equals("")) strategy1.setFull(Double.valueOf(strategy_full));
        if(strategy_discount != null && !strategy_discount.equals("")) strategy1.setDiscount(Double.valueOf(strategy_discount));
        if (strategyService.updateStrategy1(strategy1)){
            object.put("success", true);
            object.put("msg", "更新成功");
        }else{
            object.put("success", false);
            object.put("msg", "更新成功");
        }
        return object.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/restaurant/doDeleteStrategy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String doDeleteStrategy(
            HttpSession session,
            @RequestParam(value = "strategy_id") String strategy_id  /*餐品id */
    )throws ParseException {
        logger.info("餐厅删除策略");
        JSONObject object = new JSONObject();
        if(strategy_id.equals("")){
            object.put("success", false);
            object.put("msg", "不存在这个餐品");
            return object.toJSONString();
        }

        if (strategyService.deleteStrategy(1, Long.valueOf(strategy_id))){
            object.put("success", true);
            object.put("msg", "删除成功");
        }else{
            object.put("success", false);
            object.put("msg", "删除成功");
        }
        return object.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/restaurant/doAddStrategy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String doAdd(
            HttpSession session,
            @RequestParam(value = "strategy_full") String strategy_full,
            @RequestParam(value = "strategy_discount") String strategy_discount
    )throws ParseException {
        logger.info("餐厅新增促销策略");
        JSONObject object = new JSONObject();

        //logger.info("图片信息为" + food_picture);
        long res_id = (Long) session.getAttribute("restaurantId");
        Restaurant restaurant = restaurantService.getById(res_id);

        Strategy1 strategy1 = new Strategy1(Double.valueOf(strategy_full),
                Double.valueOf(strategy_discount), restaurant);
        if(strategyService.updateStrategy1(strategy1)){
            object.put("success", true);
            object.put("msg", "新增成功");
        } else {
            object.put("success", false);
            object.put("msg", "新增失败");
        }
        return object.toJSONString();
    }

    @RequestMapping(value="/restaurant/doUploadFile",method=RequestMethod.POST)
    private String fildUpload(@RequestParam(value="file",required=false) MultipartFile file,
                              HttpServletRequest request)throws Exception{
        JSONObject object = new JSONObject();
        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path="";
        if(!file.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            path="/WEB-INF/static/images/"+uuid+"."+imageName;
            file.transferTo(new File(pathRoot+path));
        }
        System.out.println(path);
        if (file != null && !file.isEmpty()) {
            logger.info("文件名为"+ file.getOriginalFilename() );
            object.put("success", true);
            object.put("msg", "上传成功");
        } else {
            object.put("success", false);
            object.put("msg", "上传失败");
        }
        return object.toJSONString();
    }

    @RequestMapping(value = "/restaurant/ok", method = RequestMethod.POST)
    public String uploadFile(
            HttpSession session,
            MultipartFile file
    ) throws Exception {
        logger.info("单文件上传");
        JSONObject object = new JSONObject();
        String filename = file.getOriginalFilename();
        String leftPath= session.getServletContext().getRealPath("/images");
        System.out.println("leftpath======" + leftPath);
        File file_1 = new File(leftPath,filename);

        if (file != null && !file.isEmpty()) {
            logger.info("文件名为"+ file.getOriginalFilename() );
            object.put("success", true);
            object.put("msg", "上传成功");
        } else {
            object.put("success", false);
            object.put("msg", "上传失败");
        }
        return object.toJSONString();
    }
}
