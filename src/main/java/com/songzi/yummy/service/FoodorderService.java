package com.songzi.yummy.service;

import com.songzi.yummy.entity.Food;
import com.songzi.yummy.entity.Foodorder;
import com.songzi.yummy.entity.Foodorderitem;
import com.songzi.yummy.entity.Member;

import java.util.Date;
import java.util.List;

public interface FoodorderService {
    Foodorder update(Foodorder foodorder);

    long getTotal();
    List<Foodorder> getAllOrders();
    List<Foodorder> getOrderByDate(Date d1, Date d2);
    List<Foodorder> getOrderByMember(Member member);
    Foodorder get(long order_id);
    List<Foodorder> getOrderByStatus(byte status);
    List<Foodorder> getOrderByResId(long res_id);
    Foodorder getOrderByOrderitem(Foodorderitem foodorderitem);
    List<Foodorder> getTotalByDate(Date beginDate, Date endDate);
}
