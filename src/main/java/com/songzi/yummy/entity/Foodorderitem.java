package com.songzi.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Foodorderitem extends BaseEntity implements Serializable {
    private Integer number;
    private Double price;
    private Long foodId;
    private Long orderId;
    private Long memId;
    private Food foodByFoodId;
    private Foodorder foodorderByOrderId;
    private Member memberByMemId;

    public Foodorderitem(){}
    public Foodorderitem(Member member, Food food, int number, Foodorder foodorder){
        this.number = number;
        this.price = food.getPrice();
        this.foodId = food.getId();
        this.memberByMemId = member;
        this.foodByFoodId = food;
        this.foodorderByOrderId = foodorder;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "food_id",insertable=false,updatable=false)
    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    @Basic
    @Column(name = "order_id",insertable=false,updatable=false)
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "mem_id",insertable=false,updatable=false)
    public Long getMemId() {
        return memId;
    }

    public void setMemId(Long memId) {
        this.memId = memId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foodorderitem that = (Foodorderitem) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(price, that.price) &&
                Objects.equals(foodId, that.foodId) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(memId, that.memId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, price, foodId, orderId, memId);
    }

    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "id", nullable = false)
    public Food getFoodByFoodId() {
        return foodByFoodId;
    }

    public void setFoodByFoodId(Food foodByFoodId) {
        this.foodByFoodId = foodByFoodId;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    public Foodorder getFoodorderByOrderId() {
        return foodorderByOrderId;
    }

    public void setFoodorderByOrderId(Foodorder foodorderByOrderId) {
        this.foodorderByOrderId = foodorderByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "mem_id", referencedColumnName = "id", nullable = false)
    public Member getMemberByMemId() {
        return memberByMemId;
    }

    public void setMemberByMemId(Member memberByMemId) {
        this.memberByMemId = memberByMemId;
    }
}
