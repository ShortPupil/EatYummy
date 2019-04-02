package com.songzi.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Food extends BaseEntity implements Serializable {

    private Long resId;
    private Timestamp starttime;
    private Timestamp endtime;
    private Integer type;
    private String name;
    private String picture;
    private Long amount;
    private Double price;
    private Restaurant restaurantByResId;

    public Food(){}

    public Food(Long resId, Timestamp starttime, Timestamp endtime, String name, String picture, Long amount,
                Double price, String description, Restaurant restaurant) {
        this.resId = resId;
        this.starttime = starttime;
        this.endtime = endtime;
        this.name = name;
        this.picture = picture;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.type = 1;
        this.restaurantByResId = restaurant;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    @Basic
    @Column(name = "res_id",insertable=false,updatable=false)
    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    @Basic
    @Column(name = "starttime")
    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    @Basic
    @Column(name = "endtime")
    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "amount")
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(resId, food.resId) &&
                Objects.equals(starttime, food.starttime) &&
                Objects.equals(endtime, food.endtime) &&
                Objects.equals(type, food.type) &&
                Objects.equals(name, food.name) &&
                Objects.equals(picture, food.picture) &&
                Objects.equals(amount, food.amount) &&
                Objects.equals(price, food.price);
                }

    @Override
    public int hashCode() {
        return Objects.hash(resId, starttime, endtime, type, name, picture, amount, price);
    }

    @ManyToOne
    @JoinColumn(name = "res_id", referencedColumnName = "id", nullable = false)
    public Restaurant getRestaurantByResId() {
        return restaurantByResId;
    }

    public void setRestaurantByResId(Restaurant restaurantByResId) {
        this.restaurantByResId = restaurantByResId;
    }
}
