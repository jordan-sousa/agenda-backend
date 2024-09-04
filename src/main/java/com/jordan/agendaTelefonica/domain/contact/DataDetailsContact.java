package com.jordan.agendaTelefonica.domain.contact;

public record DataDetailsContact (Long id, String name, String phone, String email, String image){
    public DataDetailsContact(Contact contact) {
        this(contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail(), contact.getImage());
    }
}
