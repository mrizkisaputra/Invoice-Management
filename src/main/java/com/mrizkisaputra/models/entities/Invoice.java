package com.mrizkisaputra.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@SQLDelete(sql = "UPDATE invoice SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Invoice extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_invoice_type", referencedColumnName = "id")
    private InvoiceType invoiceType;

    @OneToMany(mappedBy = "invoice")
    private List<VirtualAccount> virtualAccount;

    @NotNull @NotEmpty @Size(min = 3, max = 100)
    private String invoiceNumber;

    @NotNull @NotEmpty @Size(min = 3, max = 100)
    private String description;

    @NotNull
    private Boolean paid = false;

    @NotNull @Min(0)
    private BigDecimal amount;

    @NotNull
    private LocalDate dueDate;
}
