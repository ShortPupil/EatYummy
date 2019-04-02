package com.songzi.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Approval extends BaseEntity implements Serializable {
    private Long manId;
    private Integer oldType;
    private Integer newType;
    private Integer finished;
    private String oldCoordinate;
    private String newCoordinate;
    private Manager managerByManId;

    public Approval(){}

    @Basic
    @Column(name = "man_id",insertable=false,updatable=false)
    public Long getManId() {
        return manId;
    }

    public void setManId(Long manId) {
        this.manId = manId;
    }

    @Basic
    @Column(name = "old_type")
    public Integer getOldType() {
        return oldType;
    }

    public void setOldType(Integer oldType) {
        this.oldType = oldType;
    }

    @Basic
    @Column(name = "new_type")
    public Integer getNewType() {
        return newType;
    }

    public void setNewType(Integer newType) {
        this.newType = newType;
    }

    @Basic
    @Column(name = "finished")
    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    @Basic
    @Column(name = "old_coordinate")
    public String getOldCoordinate() {
        return oldCoordinate;
    }

    public void setOldCoordinate(String oldCoordinate) {
        this.oldCoordinate = oldCoordinate;
    }

    @Basic
    @Column(name = "new_coordinate")
    public String getNewCoordinate() {
        return newCoordinate;
    }

    public void setNewCoordinate(String newCoordinate) {
        this.newCoordinate = newCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Approval approval = (Approval) o;
        return Objects.equals(manId, approval.manId) &&
                Objects.equals(oldType, approval.oldType) &&
                Objects.equals(newType, approval.newType) &&
                Objects.equals(finished, approval.finished) &&
                Objects.equals(oldCoordinate, approval.oldCoordinate) &&
                Objects.equals(newCoordinate, approval.newCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manId, oldType, newType, finished, oldCoordinate, newCoordinate);
    }

    @ManyToOne
    @JoinColumn(name = "man_id", referencedColumnName = "id", nullable = false)
    public Manager getManagerByManId() {
        return managerByManId;
    }

    public void setManagerByManId(Manager managerByManId) {
        this.managerByManId = managerByManId;
    }
}
