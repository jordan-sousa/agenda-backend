package com.jordan.agendaTelefonica.repository;

import com.jordan.agendaTelefonica.domain.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
