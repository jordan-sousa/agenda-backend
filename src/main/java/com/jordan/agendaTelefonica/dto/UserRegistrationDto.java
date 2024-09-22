package com.jordan.agendaTelefonica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationDto(@NotNull @NotBlank String name,
                                  @NotNull @NotBlank String login,
                                  @NotNull @NotBlank String password) {
}
