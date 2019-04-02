package com.songzi.yummy.service.impl;

import com.songzi.yummy.entity.Foodorder;
import com.songzi.yummy.entity.Foodorderitem;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.repository.FoodorderRepository;
import com.songzi.yummy.service.FoodorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FoodorderServiceImpl implements FoodorderService {
    @Autowired
    FoodorderRepository foodorderRepository;

    @Override
    public Foodorder update(Foodorder foodorder){
        return foodorderRepository.saveAndFlush(foodorder);
    }

    @Override
    public List<Foodorder> getAllOrders(){
        return foodorderRepository.findAll();
    }

    @Override
    public long getTotal(){
        return foodorderRepository.count();
    }

    @Override
    public List<Foodorder> getOrderByDate(Date d1, Date d2){
        List<Foodorder> orders = foodorderRepository.findAll();
        List<Foodorder> result = new ArrayList<Foodorder>();
        for(Foodorder o : orders){
            if(Date.from(o.getCreatedAt().toInstant()).after(d1) &&
                    Date.from(o.getUpdatedAt().toInstant()).before(d2)){
                result.add(o);
            }
        }
        return result;
    }

    @Override
    public List<Foodorder> getOrderByMember(Member member){
        return foodorderRepository.findByMem(member.getId());
    }

    @Override
    public Foodorder get(long order_id){
        return foodorderRepository.getOne(order_id);
    }

    @Override
    public List<Foodorder> getOrderByStatus(byte status){ return foodorderRepository.findByStatus(status);}

    @Override
    public List<Foodorder> getOrderByResId(long res_id){ return foodorderRepository.findByResId(res_id);}

    @Override
    public Foodorder getOrderByOrderitem(Foodorderitem foodorderitem){
        List<Foodorder> foodorderList = foodorderRepository.getAll();
        for(Foodorder foodorder : foodorderList){
            if(foodorder.getFoodorderitemsById().contains(foodorderitem))
                return foodorder;
        }
        return null;
    }

    @Override
    public List<Foodorder> getTotalByDate(Date beginDate, Date endDate){
        List<Foodorder> res = new ArrayList<Foodorder>();
        List<Foodorder> allOrder = foodorderRepository.getAll();
        for(Foodorder foodorder : allOrder){
            if(foodorder.getUpdatedAt().before(endDate) && foodorder.getUpdatedAt().after(beginDate)){
                res.add(foodorder);
            }
        }
        System.out.println(res.size());
        return res;
    }
}
