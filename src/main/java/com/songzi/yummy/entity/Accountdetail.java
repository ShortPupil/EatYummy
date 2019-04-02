package com.songzi.yummy.entity;

import com.aliyuncs.ecs.model.v20140526.DescribeImageSharePermissionResponse;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Accountdetail extends BaseEntity implements Serializable {

    private Integer type;
    private Double money;
    private Long memId;
    private Long resId;
    private Member memberByMemId;
    private Restaurant restaurantByResId;

    public Accountdetail(){}
    public Accountdetail(Integer type, Double money, Member memberByMemId, Restaurant restaurantByResId) {
        this.type = type;
        this.money = money;
        this.memberByMemId = memberByMemId;
        this.restaurantByResId = restaurantByResId;
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
    @Column(name = "money")
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "mem_id",insertable=false,updatable=false)
    public Long getMemId() {
        return memId;
    }

    public void setMemId(Long memId) {
        this.memId = memId;
    }

    @Basic
    @Column(name = "res_id",insertable=false,updatable=false)
    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accountdetail that = (Accountdetail) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(money, that.money) &&
                Objects.equals(memId, that.memId) &&
                Objects.equals(resId, that.resId); }

    @Override
    public int hashCode() {
        return Objects.hash(type, money, memId, resId);
    }

    @ManyToOne
    @JoinColumn(name = "mem_id", referencedColumnName = "id", nullable = false)
    public Member getMemberByMemId() {
        return memberByMemId;
    }

    public void setMemberByMemId(Member memberByMemId) {
        this.memberByMemId = memberByMemId;
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
