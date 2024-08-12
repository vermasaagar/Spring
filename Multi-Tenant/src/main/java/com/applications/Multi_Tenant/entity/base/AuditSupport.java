package com.applications.Multi_Tenant.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AuditSupport {

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    protected LocalDateTime createdDate;

    @Column(name = "last_modified_date", nullable = false)
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    protected Long createdBy;

    @Column(name = "last_modified_by", nullable = false)
    @LastModifiedBy
    protected Long lastModifiedBy;

    @Version
    @Column(name = "version", nullable = false)
    protected Long version;
}