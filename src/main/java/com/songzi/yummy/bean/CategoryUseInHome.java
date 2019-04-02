package com.songzi.yummy.bean;

import com.songzi.yummy.entity.Category;
import com.songzi.yummy.entity.Restaurant;

import java.util.List;

public class CategoryUseInHome {

    private Category category;

    public CategoryUseInHome(Category category, List<List<Restaurant>> complexRestaurantList) {
        this.category = category;
        this.complexRestaurantList = complexRestaurantList;
    }

    List<List<Restaurant>> complexRestaurantList;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<List<Restaurant>> getComplexRestaurantList() {
        return complexRestaurantList;
    }

    public void setComplexRestaurantList(List<List<Restaurant>> complexRestaurantList) {
        this.complexRestaurantList = complexRestaurantList;
    }
}
