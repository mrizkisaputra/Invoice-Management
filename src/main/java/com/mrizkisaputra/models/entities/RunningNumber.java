package com.mrizkisaputra.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class RunningNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull
    private String id;

    @NotNull @NotEmpty @Size(min = 3, max = 100)
    private String prefix;

    @NotNull @Min(0)
    private Long lastNumber = 0L;
}
