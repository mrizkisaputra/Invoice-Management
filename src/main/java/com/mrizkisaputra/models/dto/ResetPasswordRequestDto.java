package com.mrizkisaputra.models.dto;

import com.mrizkisaputra.models.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResetPasswordRequestDto {

    private User user;

    @NotNull @NotEmpty
    private String password;

    @NotNull @NotEmpty
    private String confirmPassword;
}
