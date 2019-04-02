package com.songzi.yummy.repository;

import com.songzi.yummy.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    //需要重新拼接
    @Query("select f from Restaurant f where f.name like '%?1%'")
    List<Restaurant> findByNameContaining(@Param("name") String name);

    @Query("select r from Restaurant r where r.categoryId =:category_id")
    List<Restaurant> findRestaurantsByCategory(@Param("category_id") long category_id);

    @Query("select r from Restaurant r where r.codenumber =:codenumber")
    Restaurant findRestaurantsByCodenumber(@Param("codenumber") String codenumber);
}
