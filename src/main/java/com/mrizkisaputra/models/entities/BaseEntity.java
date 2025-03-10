package com.mrizkisaputra.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Soft Delete
    @Enumerated(EnumType.STRING)
    private StatusRecord statusRecord = StatusRecord.ACTIVE;

    // Audit Trail
    @CreatedBy
    private String lastCreatedBy;
    @LastModifiedBy
    private String lastUpdatedBy;
    @CreatedDate
    private LocalDateTime lastCreatedAt;
    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;
}
