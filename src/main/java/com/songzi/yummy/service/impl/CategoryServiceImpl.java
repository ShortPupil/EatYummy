package com.songzi.yummy.service.impl;

import com.songzi.yummy.entity.Category;
import com.songzi.yummy.entity.Restaurant;
import com.songzi.yummy.repository.CategoryRepository;
import com.songzi.yummy.repository.RestaurantRepository;
import com.songzi.yummy.service.CategoryService;
import com.songzi.yummy.service.RestaurantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    protected Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoryForHomePage(){
        List<Category> categoryList = categoryRepository.findAll();
        for(Category category : categoryList){
            logger.info("获取分类id为{}的产品集合，按产品ID倒序排序", category.getId());
            List<Restaurant> restaurantList = restaurantRepository.findRestaurantsByCategory(category.getId());
            if (restaurantList != null) {
                for (Restaurant restaurant : restaurantList) {
                    long res_id = restaurant.getId();
                    logger.info("获取产品id为{}的产品预览图片信息", res_id);
                }
            }
            //category中间要加一个属性叫做RestaurantList
            //category.setRestaurantList(restaurantList);
        }
        return categoryList;
    }

    @Override
    public Category get(long category_id){return categoryRepository.getOne(category_id);}
}
