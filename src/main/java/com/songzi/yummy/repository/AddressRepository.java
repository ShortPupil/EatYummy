package com.songzi.yummy.repository;

import com.songzi.yummy.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.memId=:member_id")
    public ArrayList<Address> findAddressByMemId(@Param("member_id") long member_id);
}
