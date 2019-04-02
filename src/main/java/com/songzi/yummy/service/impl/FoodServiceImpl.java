package com.songzi.yummy.service.impl;

import com.songzi.yummy.entity.Food;
import com.songzi.yummy.entity.Foodorder;
import com.songzi.yummy.repository.FoodRepository;
import com.songzi.yummy.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Food food){
        foodRepository.saveAndFlush(food);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteById(long food_id){
        foodRepository.deleteById(food_id);
        return true;
    }

    @Override
    public Food get(long id){
        return foodRepository.getOne(id);
        //return foodRepository.;
    }

    @Override
    public List<Food> getAllFood(){ return foodRepository.findAll();}

    @Override
    public List<Food> getFoodByRestaurant(long res_id){
        return foodRepository.findFoodByResId(res_id);
    }

    @Override
    public List<Food> getFoodByName(String name){
        return foodRepository.findByNameContaining(name);
    }

    @Override
    public List<Food> getFoodByPrice(double lowerPrice, double upperPrice){
        return foodRepository.findFoodByPrice(lowerPrice, upperPrice);
    }

    @Override
    public long getTotal(){
        return foodRepository.count();
    }
}
