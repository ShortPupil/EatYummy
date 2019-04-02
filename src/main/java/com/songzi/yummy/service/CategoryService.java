package com.songzi.yummy.service;

import com.songzi.yummy.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryForHomePage();
    Category get(long category_id);
}
