package com.mrizkisaputra.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity @Table(name = "s_reset_password")
public class ResetPassword {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private LocalDateTime generated = LocalDateTime.now();

    @NotNull
    private LocalDateTime expired = LocalDateTime.now()
            .plusDays(3);

    @NotNull
    private String uniqueCode;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;
}
