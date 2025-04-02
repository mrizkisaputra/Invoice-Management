package com.mrizkisaputra.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @NotNull @NotEmpty
    private String name;

    @NotNull @Email @Size(max = 100)
    private String email;

    @NotNull @NotEmpty @Size(max = 13)
    private String phoneNumber;
}
