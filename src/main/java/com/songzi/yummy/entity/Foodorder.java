package com.songzi.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Foodorder extends BaseEntity implements Serializable {

    private Long addressId;
    private String receiver;
    private String phonenumber;
    private String userMessage;
    private Timestamp payDate;
    private Timestamp deliveryDate;
    private Timestamp confirmDate;
    private Byte status;
    private double needTime;
    private Double totalPrice;
    private Long memId;
    private Long resId;
    private Address addressByAddressId;
    private Member memberByMemId;
    private Restaurant restaurantByResId;
    private List<Foodorderitem> foodorderitemsById;

    public Foodorder(){}

    public Foodorder(Byte status, Address addressByAddressId, Member memberByMemId, Restaurant restaurantByResId, List<Foodorderitem> foodorderitemsById) {
        this.status = status;
        this.addressByAddressId = addressByAddressId;
        this.memberByMemId = memberByMemId;
        this.restaurantByResId = restaurantByResId;
        this.foodorderitemsById = foodorderitemsById;
    }

    @Column(name = "total_price")
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "address_id", insertable=false,updatable=false)
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Column(name = "need_time")
    public double getNeedTime() {
        return needTime;
    }

    public void setNeedTime(double needTime) {
        this.needTime = needTime;
    }

    @Column(name = "receiver")
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Column(name = "phonenumber")
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Column(name = "user_message")
    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    @Column(name = "pay_date")
    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    @Column(name = "delivery_date")
    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Column(name = "confirm_date")
    public Timestamp getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Timestamp confirmDate) {
        this.confirmDate = confirmDate;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        Foodorder foodorder = (Foodorder) o;
        return Objects.equals(addressId, foodorder.addressId) &&
                Objects.equals(receiver, foodorder.receiver) &&
                Objects.equals(phonenumber, foodorder.phonenumber) &&
                Objects.equals(userMessage, foodorder.userMessage) &&
                Objects.equals(payDate, foodorder.payDate) &&
                Objects.equals(deliveryDate, foodorder.deliveryDate) &&
                Objects.equals(confirmDate, foodorder.confirmDate) &&
                Objects.equals(status, foodorder.status) &&
                Objects.equals(memId, foodorder.memId) &&
                Objects.equals(resId, foodorder.resId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, receiver, phonenumber, userMessage, payDate, deliveryDate, confirmDate, status, memId, resId);
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
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

    @OneToMany(mappedBy = "foodorderByOrderId")
    public List<Foodorderitem> getFoodorderitemsById() {
        return foodorderitemsById;
    }
    public void setFoodorderitemsById(List<Foodorderitem> foodorderitemsById) {
        this.foodorderitemsById = foodorderitemsById;
    }
}
