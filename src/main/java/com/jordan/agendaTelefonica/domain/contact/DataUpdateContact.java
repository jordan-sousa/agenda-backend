package com.jordan.agendaTelefonica.domain.contact;

import jakarta.validation.constraints.NotNull;

public record DataUpdateContact(@NotNull Long id, String name, String phone) {
}
