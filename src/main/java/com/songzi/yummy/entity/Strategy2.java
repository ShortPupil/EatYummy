package com.songzi.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "strategy_2")
public class Strategy2 extends BaseEntity implements Serializable {
    private String foodIds;
    private double discountPrice;
    private Double primePrice;
    private Restaurant restaurantByResId;

    private long resId;

    public Strategy2(){}

    @Basic
    @Column(name = "food_ids")
    public String getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(String foodIds) {
        this.foodIds = foodIds;
    }

    @Basic
    @Column(name = "discount_price")
    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Basic
    @Column(name = "prime_price")
    public Double getPrimePrice() {
        return primePrice;
    }

    public void setPrimePrice(Double primePrice) {
        this.primePrice = primePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strategy2 strategy2 = (Strategy2) o;
        return Objects.equals(foodIds, strategy2.foodIds) &&
                Objects.equals(primePrice, strategy2.primePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodIds, discountPrice, primePrice);
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
    @Column(name = "res_id",insertable=false,updatable=false)
    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
    }
}
