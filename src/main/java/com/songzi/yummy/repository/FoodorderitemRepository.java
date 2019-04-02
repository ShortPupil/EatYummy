package com.songzi.yummy.repository;

import com.songzi.yummy.entity.Foodorderitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodorderitemRepository extends JpaRepository<Foodorderitem, Long> {

    /**通过foodorder的id查找其单项*/
    @Query("select f from Foodorderitem f where f.orderId = :foodorder_id")
    List<Foodorderitem> findByFoodorderId(@Param("foodorder_id") long foodorder_id);

    @Query("select f from Foodorderitem f where f.memId = :mem_id")
    List<Foodorderitem> findByMemId(@Param("mem_id") long mem_id);
}
