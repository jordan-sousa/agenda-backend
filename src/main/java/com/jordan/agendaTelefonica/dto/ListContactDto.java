package com.jordan.agendaTelefonica.dto;

import com.jordan.agendaTelefonica.domain.contact.Contact;

public record ListContactDto(Long id,
                             String name,
                             String phone,
                             String email) {

    public ListContactDto(Contact contact) {
        this(contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail());
    }
}
