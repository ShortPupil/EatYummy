package com.songzi.yummy.bean;

import com.songzi.yummy.entity.Foodorderitem;

import java.util.List;

public class Cart {
    public List<Foodorderitem> getFoodorderitemList() {
        return foodorderitemList;
    }

    public void setFoodorderitemList(List<Foodorderitem> foodorderitemList) {
        this.foodorderitemList = foodorderitemList;
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public long getRes_id() {
        return res_id;
    }

    public void setRes_id(long res_id) {
        this.res_id = res_id;
    }

    private List<Foodorderitem> foodorderitemList;
    private long member_id;
    private long res_id;

    public Cart(){}
    public Cart(List<Foodorderitem> foodorderitemList, long member_id, long res_id) {
        this.foodorderitemList = foodorderitemList;
        this.member_id = member_id;
        this.res_id = res_id;
    }
}
