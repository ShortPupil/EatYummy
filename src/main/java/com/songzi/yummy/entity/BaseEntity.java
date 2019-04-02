package com.songzi.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * An abstract base model class for entities
 */
@MappedSuperclass
public abstract class BaseEntity implements Comparable<BaseEntity>, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4349990425062737386L;

    private Long id;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = updatedAt = new Timestamp(System.currentTimeMillis()); ;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis()); ;
    }

    @Override
    public int compareTo(BaseEntity o) {
        return this.getId().compareTo(o.getId());
    }

    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }

        return this.getId().equals(((BaseEntity) other).getId());
    }

   /* public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long _id) {
        id = _id;
    }

    @Column(nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Column(nullable = false)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
