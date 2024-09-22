package com.jordan.agendaTelefonica.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateContactDto(@NotNull Long id, String name, String phone) {
}
