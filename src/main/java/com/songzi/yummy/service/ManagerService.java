package com.songzi.yummy.service;

import com.songzi.yummy.entity.Manager;

import java.util.List;

public interface ManagerService {
    boolean update(Manager manager);

    List<Manager> searchByName(String name);
    Manager get(long id);
    Manager login(String id, String password);

    long getTotal();
}
