package com.songzi.yummy.service.impl;

import com.songzi.yummy.entity.Address;
import com.songzi.yummy.repository.AddressRepository;
import com.songzi.yummy.service.AddressService;
import com.songzi.yummy.util.map.MapNavUtil;
import com.songzi.yummy.util.map.TestMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public ArrayList<Address> getAddressByMemId(long member_id){
        return addressRepository.findAddressByMemId(member_id);
    }

    @Override
    public Address getById(long address_id){
        return addressRepository.getOne(address_id);
    }

    @Override
    public double getNeedTime(String res_a1, String mem_a2){
        String origin=res_a1;//出发点经纬度
        String destination=mem_a2;//目的地经纬度

        System.out.println(origin);
        System.out.println(destination);

        MapNavUtil mapResult=new MapNavUtil(origin, destination, TestMap.key);

        return Double.valueOf(mapResult.getResults().getDuration());
    }
}
