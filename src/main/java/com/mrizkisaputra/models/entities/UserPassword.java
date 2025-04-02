package com.mrizkisaputra.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "s_password")
@Data
public class UserPassword {

    @Id
    @Column(name = "id_user")
    private String id;

    @OneToOne() @MapsId
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @NotNull @Size(max = 255)
    private String password;
}
