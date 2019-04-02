package com.songzi.yummy.repository;

import com.songzi.yummy.entity.Food;
import com.songzi.yummy.entity.Foodorder;
import com.songzi.yummy.entity.Foodorderitem;
import com.songzi.yummy.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodorderRepository extends JpaRepository<Foodorder, Long> {

    @Query("select f from Foodorder f where f.memId = :mem_id and f.status <> 0")
    List<Foodorder> findByMem(@Param("mem_id") long mem_id);

    @Query("select f from Foodorder f where f.status = :status and f.status <> 0")
    List<Foodorder> findByStatus(@Param("status") byte status);

    @Query("select f from Foodorder f where f.resId = :res_id and f.status <> 0")
    List<Foodorder> findByResId(@Param("res_id") long res_id);

    @Query("select f from Foodorder f")
    List<Foodorder> getAll();
}
