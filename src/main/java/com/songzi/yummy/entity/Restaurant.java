package com.songzi.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Restaurant extends BaseEntity implements Serializable {

    private String password;
    private Integer type;
    private String picture;
    private Long accountId;
    private String coordinate;
    private String codenumber;
    private String name;
    private Long categoryId;
    private Account accountByAccountId;
    private Category categoryByCategoryId;
    private Collection<Accountdetail> accountdetailsById;
    private Collection<Food> foodsById;
    private Collection<Foodorder> foodordersById;
    private Collection<Strategy1> strategy1sById;
    private Collection<Strategy2> strategy2sById;
    private String localAddress;

    public Restaurant(){}
    /**restaurant_password, restaurant_type, restaurant_address,
     restaurant_coordinate, codenumber, restaurant_name, restaurant_category*/
    public Restaurant(String password, int type, String address,String coordinate,
                      String codenumber, String name, long categoryId, Category category, Account account){
        this.password = password;
        this.type = type;
        this.coordinate = coordinate;
        this.localAddress = address;
        this.codenumber = codenumber;
        this.name = name;
        this.categoryId = categoryId;
        this.categoryByCategoryId = category;
        this.accountByAccountId = account;
        this.accountId = account.getId();
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "account_id",insertable=false,updatable=false)
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "coordinate")
    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    @Basic
    @Column(name = "codenumber")
    public String getCodenumber() {
        return codenumber;
    }

    public void setCodenumber(String codenumber) {
        this.codenumber = codenumber;
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
    @Column(name = "category_id",insertable=false,updatable=false)
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "local_address")
    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(password, that.password) &&
                Objects.equals(type, that.type) &&
                Objects.equals(picture, that.picture) &&
                Objects.equals(accountId, that.accountId) &&
                Objects.equals(coordinate, that.coordinate) &&
                Objects.equals(codenumber, that.codenumber) &&
                Objects.equals(name, that.name) &&
                Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, type, picture, accountId, coordinate, codenumber, name, categoryId);
    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    public Account getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(Account accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(Category categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @OneToMany(mappedBy = "restaurantByResId")
    public Collection<Accountdetail> getAccountdetailsById() {
        return accountdetailsById;
    }

    public void setAccountdetailsById(Collection<Accountdetail> accountdetailsById) {
        this.accountdetailsById = accountdetailsById;
    }

    @OneToMany(mappedBy = "restaurantByResId")
    public Collection<Food> getFoodsById() {
        return foodsById;
    }

    public void setFoodsById(Collection<Food> foodsById) {
        this.foodsById = foodsById;
    }

    @OneToMany(mappedBy = "restaurantByResId")
    public Collection<Foodorder> getFoodordersById() {
        return foodordersById;
    }

    public void setFoodordersById(Collection<Foodorder> foodordersById) {
        this.foodordersById = foodordersById;
    }

    @OneToMany(mappedBy = "restaurantByResId")
    public Collection<Strategy1> getStrategy1sById() {
        return strategy1sById;
    }

    public void setStrategy1sById(Collection<Strategy1> strategy1sById) {
        this.strategy1sById = strategy1sById;
    }

    @OneToMany(mappedBy = "restaurantByResId")
    public Collection<Strategy2> getStrategy2sById() {
        return strategy2sById;
    }

    public void setStrategy2sById(Collection<Strategy2> strategy2sById) {
        this.strategy2sById = strategy2sById;
    }
}
