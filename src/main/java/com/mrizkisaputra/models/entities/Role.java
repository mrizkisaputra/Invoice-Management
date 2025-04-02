package com.mrizkisaputra.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "s_roles")
@Data
public class Role {

    @Id
    @NotNull @Size(max = 100)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull @Size(max = 100)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
