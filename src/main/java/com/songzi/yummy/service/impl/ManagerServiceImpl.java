package com.songzi.yummy.service.impl;

import com.songzi.yummy.entity.Manager;
import com.songzi.yummy.repository.ManagerRepository;
import com.songzi.yummy.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Manager manager){
        managerRepository.saveAndFlush(manager);
        return true;
    }

    @Override
    public List<Manager> searchByName(String name){
        return managerRepository.searchManagerByName(name);
    }

    @Override
    public Manager get(long id){
        return managerRepository.getOne(id);
    }

    @Override
    public Manager login(String id, String password){
        Manager manager = managerRepository.getOne(Long.valueOf(id));
        if(manager.equals(null)) return null;
        if(manager.getPassword().equals(password)){
            return manager;
        }
        return null;
    }

    @Override
    public long getTotal(){
        return managerRepository.count();
    }
}
