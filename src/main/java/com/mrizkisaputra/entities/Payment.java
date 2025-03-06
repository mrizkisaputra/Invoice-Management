package com.mrizkisaputra.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@SQLDelete(sql = "UPDATE payment SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Payment extends BaseEntity {
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "id_virtual_account", referencedColumnName = "id")
    private VirtualAccount virtualAccount;

    @NotNull
    private LocalDateTime transactionTime;

    @NotNull @NotEmpty
    private String providerReference;

    @NotNull @Min(1)
    private BigDecimal amount;
}
