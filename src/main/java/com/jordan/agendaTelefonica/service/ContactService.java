package com.jordan.agendaTelefonica.service;

import com.jordan.agendaTelefonica.domain.contact.Contact;
import com.jordan.agendaTelefonica.dto.CreateContactDto;
import com.jordan.agendaTelefonica.dto.DetailsContactDto;
import com.jordan.agendaTelefonica.dto.ListContactDto;
import com.jordan.agendaTelefonica.dto.UpdateContactDto;
import com.jordan.agendaTelefonica.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ResponseEntity createContact(CreateContactDto data, UriComponentsBuilder uriBuilder) {
        var contact = new Contact(data);
        System.out.println(data);
        contactRepository.save(contact);

        var uri = uriBuilder.path("/contacts/{id}").buildAndExpand(contact.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsContactDto(contact));
    }

    public Page<ListContactDto> listContact(Pageable pagination) {
        return contactRepository.findAll(pagination).map(ListContactDto::new);
    }

    public ResponseEntity<DetailsContactDto> updateContact(Long id, UpdateContactDto data) throws ChangeSetPersister.NotFoundException {
        // Usar findById para garantir que o contato exista
        var contact = contactRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException()); // Lança exceção 404 se o contato não for encontrado

        // Atualiza informações do contato com dados do DTO
        contact.updateInformation(data);
        contactRepository.save(contact); // Salvar as mudanças feitas no contato

        return ResponseEntity.ok(new DetailsContactDto(contact));
    }

    public ResponseEntity deleteContact(@PathVariable Long id) {
        var contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
