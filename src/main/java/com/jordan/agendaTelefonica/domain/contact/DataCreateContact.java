package com.jordan.agendaTelefonica.domain.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataCreateContact(
                                @NotBlank
                                String name,

                                @NotBlank
                                @Pattern(regexp = "\\d{11}")
                                String phone,

                                @NotBlank
                                @Email
                                String email,

                                String image) {
    public DataCreateContact(Contact contact) {
        this(contact.getName(), contact.getPhone(), contact.getEmail(), contact.getImage());
    }
}
