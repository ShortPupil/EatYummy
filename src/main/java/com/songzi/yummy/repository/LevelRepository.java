package com.songzi.yummy.repository;

import com.songzi.yummy.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level, String> {

    @Query("select l from Level l")
    List<Level> getAll();
}
