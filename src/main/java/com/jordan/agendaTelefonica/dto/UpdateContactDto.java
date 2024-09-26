package com.jordan.agendaTelefonica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateContactDto(  Long id,
                                 @NotBlank(message = "O nome não pode estar vazio")
                                 String name,

                                 @Pattern(regexp = "^[0-9]{10,11}$", message = "Número de telefone deve ter 10 ou 11 dígitos")
                                 String phone
) {
}
