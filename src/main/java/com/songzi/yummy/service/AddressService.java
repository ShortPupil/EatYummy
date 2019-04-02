package com.songzi.yummy.service;

import com.songzi.yummy.entity.Address;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface AddressService {

    ArrayList<Address> getAddressByMemId(long member_id);
    Address getById(long id);
    double getNeedTime(String a1, String a2);
}
