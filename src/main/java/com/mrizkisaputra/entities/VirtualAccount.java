package com.mrizkisaputra.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Data
@SQLDelete(sql = "UPDATE virtual_account SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class VirtualAccount extends BaseEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_payment_provider", referencedColumnName = "id")
    private PaymentProvider paymentProvider;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_invoice", referencedColumnName = "id")
    private Invoice invoice;

    @OneToMany(mappedBy = "virtualAccount")
    private List<Payment> payment;

    @NotNull @NotEmpty @Size(min = 1)
    private String accountNumber;

    @NotNull @NotEmpty @Size(min = 1)
    private String companyId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VirtualAccountType virtualAccountType = VirtualAccountType.CLOSED;
}
