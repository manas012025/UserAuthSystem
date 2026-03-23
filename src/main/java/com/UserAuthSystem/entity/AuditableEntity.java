package com.UserAuthSystem.entity;

import java.time.LocalDateTime;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
@Audited
public abstract class AuditableEntity {

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.createdBy = getCurrentUser();   // custom method
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
        this.lastModifiedBy = getCurrentUser();
    }

    private String getCurrentUser() {
        // You can fetch from Spring Security
        try {
//            return org.springframework.security.core.context.SecurityContextHolder
//                    .getContext()
//                    .getAuthentication()
//                    .getName();
        	return "USER";
        } catch (Exception e) {
            return "SYSTEM";
        }
    }

    // Getters & Setters
}
