package com.songzi.yummy.repository;

import com.songzi.yummy.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    @Query("select a from Manager a where a.name like ?1")
    List<Manager> searchManagerByName(String name);
}
