package com.songzi.yummy.service;

import com.songzi.yummy.entity.Category;
import com.songzi.yummy.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    long getTotal();
    Restaurant getById(long res_id);
    boolean update(Restaurant restaurant);

    List<Restaurant> getRestaurantsByName(String name);

    List<Restaurant> getRestaurantByCategory(long category_id);

    Restaurant login(String codenumber, String password);
}
