package com.songzi.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Member extends BaseEntity implements Serializable {

    public Member(String email, String name, String password, int state, String validateCode) {
        this.email = email;
        this.name = name;
        this.password = password;
        setLevelPoint(0);
        this.state = state;
    }

    public Member(String email, String name, String password, int state, String validateCode, Account accountByAccountId) {
        this.email = email;
        this.name = name;
        this.password = password;
        setLevelPoint(0);
        this.state = state;
        this.accountByAccountId = accountByAccountId;
    }

    public Member() {
    }

    private String email;
    private String name;
    private String phoneNumber;
    private String preferredAddress;
    private String password;
    private Integer levelPoint;
    private Long accountId;
    private String picture;
    private Integer state;
    private Account accountByAccountId;
    private String validateCode;

    @Column(name = "validate_code")
    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "preferred_address")
    public String getPreferredAddress() {
        return preferredAddress;
    }

    public void setPreferredAddress(String preferredAddress) {
        this.preferredAddress = preferredAddress;
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
    @Column(name = "level_point")
    public Integer getLevelPoint() {
        return levelPoint;
    }

    public void setLevelPoint(Integer levelPoint) {
        this.levelPoint = levelPoint;
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
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(email, member.email) &&
                Objects.equals(name, member.name) &&
                Objects.equals(phoneNumber, member.phoneNumber) &&
                Objects.equals(preferredAddress, member.preferredAddress) &&
                Objects.equals(password, member.password) &&
                Objects.equals(levelPoint, member.levelPoint) &&
                Objects.equals(accountId, member.accountId) &&
                Objects.equals(picture, member.picture) &&
                Objects.equals(state, member.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash( email, name, phoneNumber, preferredAddress, password, levelPoint, accountId, picture, state);
    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    public Account getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(Account accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }
}
