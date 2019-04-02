package com.songzi.yummy.entity;

import com.songzi.yummy.entity.Restaurant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "strategy_1")
public class Strategy1 extends BaseEntity implements Serializable {
    private Double full;
    private Double discount;
    private Restaurant restaurantByResId;
    private long resId;

    public Strategy1(){}

    public Strategy1(Double full, Double discount, Restaurant restaurantByResId) {
        this.full = full;
        this.discount = discount;
        this.restaurantByResId = restaurantByResId;
    }

    @Basic
    @Column(name = "full")
    public Double getFull() {
        return full;
    }

    public void setFull(Double full) {
        this.full = full;
    }

    @Basic
    @Column(name = "discount")
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strategy1 strategy1 = (Strategy1) o;
        return Objects.equals(full, strategy1.full) &&
                Objects.equals(discount, strategy1.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(full, discount);
    }

    @ManyToOne
    @JoinColumn(name = "res_id", referencedColumnName = "id")
    public Restaurant getRestaurantByResId() {
        return restaurantByResId;
    }

    public void setRestaurantByResId(Restaurant restaurantByResId) {
        this.restaurantByResId = restaurantByResId;
    }

    @Basic
    @Column(name = "res_id", insertable=false,updatable=false)
    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
    }
}
