package com.mrizkisaputra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
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
