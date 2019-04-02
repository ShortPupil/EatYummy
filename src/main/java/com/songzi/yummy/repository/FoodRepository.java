package com.songzi.yummy.repository;

import com.songzi.yummy.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    //需要重新拼接
    @Query("select f from Food f where f.name like %:name% and f.type<>0")
    List<Food> findByNameContaining(@Param("name") String name);

    @Query("select f from Food f where f.resId = :res_id and f.type<>0")
    List<Food> findFoodByResId(@Param("res_id") long res_id);

    @Query("select f from Food f where f.price >=:lower_price and f.price <=:upper_price and f.type<>0")
    List<Food> findFoodByPrice(@Param("lower_price") double lower_price, @Param("upper_price") double upper_price);

    @Query("select f from Food f where f.starttime <= :time and f.endtime >= :time and f.type<>0")
    List<Food> findFoodByDate(@Param("time") Timestamp time);
}
