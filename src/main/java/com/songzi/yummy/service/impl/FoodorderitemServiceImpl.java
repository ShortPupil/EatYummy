package com.songzi.yummy.service.impl;

import com.songzi.yummy.entity.Foodorder;
import com.songzi.yummy.entity.Foodorderitem;
import com.songzi.yummy.repository.FoodorderRepository;
import com.songzi.yummy.repository.FoodorderitemRepository;
import com.songzi.yummy.service.FoodorderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodorderitemServiceImpl implements FoodorderitemService {
    @Autowired
    FoodorderitemRepository foodorderitemRepository;

    @Override
    public Foodorderitem update(Foodorderitem foodorderitem){
        return foodorderitemRepository.saveAndFlush(foodorderitem);
    }

    @Override
    public List<Foodorderitem> getOrderitemListByOrderId(long foodorder_id){
        return foodorderitemRepository.findByFoodorderId(foodorder_id);
    }

    @Override
    public Foodorderitem get(long foodorderitem_id){
        return foodorderitemRepository.getOne(foodorderitem_id);
    }

    @Override
    public  List<Foodorderitem> getOrderitemListByMemId(long member_id){ return foodorderitemRepository.findByMemId(member_id);}

    @Override
    public boolean deleteList(long [] orderItem_ids){
        for(long id : orderItem_ids){
            Optional<Foodorderitem> foodorderitem = foodorderitemRepository.findById(id);
            if(foodorderitem.isPresent()){
                foodorderitemRepository.delete(foodorderitem.get());
            }
            else{
                return false;
            }
        }
        return true;
    }
}
