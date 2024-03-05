package com.interview.project.entity;

import com.interview.project.security.ProfileLocal;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @Column(columnDefinition = "CHAR(36)", updatable = false)
    String createdBy;

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    Date modifiedAt;

    @Column(columnDefinition = "CHAR(36)")
    String modifiedBy;

    @PrePersist
    public void preInsert() {
        this.createdAt = new Date();
        this.createdBy = ProfileLocal.getUserId();
    }

    @PreUpdate
    void preUpdate() {
        this.modifiedAt = new Date();
        this.modifiedBy = ProfileLocal.getUserId();
    }
}
