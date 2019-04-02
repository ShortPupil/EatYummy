package com.songzi.yummy.repository;

import com.songzi.yummy.entity.Accountdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountdetailRepository extends JpaRepository<Accountdetail, Long> {
}
