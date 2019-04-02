package com.songzi.yummy.service.impl;

import com.songzi.yummy.entity.Strategy1;
import com.songzi.yummy.entity.Strategy2;
import com.songzi.yummy.repository.Strategy1Repository;
import com.songzi.yummy.repository.Strategy2Repository;
import com.songzi.yummy.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrategyServiceImpl implements StrategyService {

    @Autowired
    private Strategy1Repository strategy1Repository;
    @Autowired
    private Strategy2Repository strategy2Repository;

    @Override
    public Strategy1 getStrategy1ById(long strategy1_id){
        return strategy1Repository.getOne(strategy1_id);
    }

    @Override
    public boolean updateStrategy1(Strategy1 strategy1){
        if(strategy1Repository.saveAndFlush(strategy1) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStrategy2(Strategy2 strategy2){
        if(strategy2Repository.saveAndFlush(strategy2) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStrategy(int state, long id){
        if(state == 1){
            strategy1Repository.deleteById(id);
            return true;
        }
        else {
            strategy2Repository.deleteById(id);
            return true;
        }
    }
}
