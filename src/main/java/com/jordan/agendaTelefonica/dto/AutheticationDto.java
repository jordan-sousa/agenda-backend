package com.jordan.agendaTelefonica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutheticationDto(@NotNull @NotBlank String login, @NotNull @NotBlank String password) {
}
