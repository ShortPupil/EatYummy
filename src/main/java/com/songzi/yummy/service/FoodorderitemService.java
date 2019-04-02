package com.songzi.yummy.service;

import com.songzi.yummy.entity.Food;
import com.songzi.yummy.entity.Foodorderitem;

import java.util.List;

/**get..*/
public interface FoodorderitemService {
    Foodorderitem update(Foodorderitem foodorderitem);

    List<Foodorderitem> getOrderitemListByOrderId(long foodorder_id);
    List<Foodorderitem> getOrderitemListByMemId(long member_id);
    Foodorderitem get(long foodorderitem_id);
    boolean deleteList(long [] orderItem_ids);
}
