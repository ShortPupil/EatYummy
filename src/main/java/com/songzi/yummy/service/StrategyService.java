package com.songzi.yummy.service;

import com.songzi.yummy.entity.Strategy1;
import com.songzi.yummy.entity.Strategy2;

public interface StrategyService {
    Strategy1 getStrategy1ById(long strategy1_id);
    boolean updateStrategy1(Strategy1 strategy1);
    boolean updateStrategy2(Strategy2 strategy2);
    boolean deleteStrategy(int state, long id);
}
