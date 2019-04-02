package com.songzi.yummy.service.impl;

import com.songzi.yummy.entity.Category;
import com.songzi.yummy.entity.Restaurant;
import com.songzi.yummy.repository.CategoryRepository;
import com.songzi.yummy.repository.RestaurantRepository;
import com.songzi.yummy.service.RestaurantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    protected Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public long getTotal(){
        return restaurantRepository.count();
    }

    @Override
    public List<Restaurant> getRestaurantsByName(String name){
        if(name == null || name.equals(null)){
            return restaurantRepository.findAll();
        }
        return restaurantRepository.findByNameContaining(name);
    }

    @Override
    public List<Restaurant> getRestaurantByCategory(long category_id){
        return restaurantRepository.findRestaurantsByCategory(category_id);
    }

    @Override
    public Restaurant getById(long res_id){
        return restaurantRepository.getOne(res_id);
    }

    @Override
    public Restaurant login(String codenumber, String password){
        if(codenumber.equals(null) || codenumber.length() != 7) return null;
        Restaurant restaurant = restaurantRepository.findRestaurantsByCodenumber(codenumber);
        if(restaurant.getPassword().equals(password)){
            return restaurant;
        }
        return null;
    }

    @Override
    public boolean update(Restaurant restaurant){
        Restaurant restaurant1 = restaurantRepository.saveAndFlush(restaurant);
        if(restaurant1.equals(null)) return false;
        return true;
    }
}
