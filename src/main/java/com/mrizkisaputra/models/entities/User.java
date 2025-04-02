package com.mrizkisaputra.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "s_users")
@Data
public class User {

    @Id
    @NotNull
    @Size(max = 100)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull @Size(max = 100)
    private String username;

    @NotNull
    private Boolean active = false;

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<ResetPassword> resetPassword;
}
