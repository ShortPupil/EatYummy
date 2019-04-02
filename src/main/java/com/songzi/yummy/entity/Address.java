package com.songzi.yummy.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Address extends BaseEntity implements Serializable {
    /**未设置外键*/
    private Long memId;
    private Integer priority;
    private String coordinate;
    private String localAddress;

    public Address(Long memId, Integer priority, String coordinate, String localAddress) {
        this.memId = memId;
        this.priority = priority;
        this.coordinate = coordinate;
        this.localAddress = localAddress;
    }

    @Basic
    @Column(name = "local_address")
    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public Address(){}

    @Basic
    @Column(name = "mem_id")
    public Long getMemId() {
        return memId;
    }

    public void setMemId(Long memId) {
        this.memId = memId;
    }

    @Basic
    @Column(name = "priority")
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "coordinate")
    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(memId, address.memId) &&
                Objects.equals(priority, address.priority) &&
                Objects.equals(coordinate, address.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memId, priority, coordinate);
    }
}
