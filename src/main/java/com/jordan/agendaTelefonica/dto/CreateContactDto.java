package com.jordan.agendaTelefonica.dto;

import com.jordan.agendaTelefonica.domain.contact.Contact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateContactDto(
                                @NotBlank
                                String name,

                                @NotBlank
                                @Pattern(regexp = "\\d{11}")
                                String phone,

                                @NotBlank
                                @Email
                                String email) {
    public CreateContactDto(Contact contact) {
        this(contact.getName(), contact.getPhone(), contact.getEmail());
    }
}
