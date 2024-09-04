package com.jordan.agendaTelefonica.domain.contact;

public record DataListContact(Long id,
                              String name,
                              String phone,
                              String email,
                              String image) {

    public DataListContact(Contact contact) {
        this(contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail(), contact.getImage());
    }
}
