package com.songzi.yummy.service;

import com.songzi.yummy.entity.Food;
import com.songzi.yummy.entity.Foodorder;

import java.util.Date;
import java.util.List;

public interface FoodService {

    boolean update(Food product);
    boolean deleteById(long food_id);
    Food get(long id);

    List<Food> getAllFood();

    List<Food> getFoodByRestaurant(long res_id);

    List<Food> getFoodByName(String name);

    List<Food> getFoodByPrice(double lowerPrice, double upperPrice);

    long getTotal();
}
