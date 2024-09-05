package com.jordan.agendaTelefonica.controller;

import com.jordan.agendaTelefonica.domain.contact.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createContact(@RequestBody @Valid DataCreateContact data, UriComponentsBuilder uriBuilder) {
        var contact = new Contact(data);
        System.out.println(data);
        contactRepository.save(contact);

        var uri = uriBuilder.path("/contacts/{id}").buildAndExpand(contact.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsContact(contact));
    }

    @GetMapping
    public ResponseEntity<Page<DataListContact>> listContact(@PageableDefault(size = 10, sort = {"name"})Pageable pagination) {
        var page = contactRepository.findAll(pagination).map(DataListContact::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateContact(@RequestBody @Valid DataUpdateContact data) {
        var contact = contactRepository.getReferenceById(data.id());
        contact.updateInformation(data);

        return  ResponseEntity.ok(new DataDetailsContact(contact));
    }

    @DeleteMapping("/{id}")
    @Transactional
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
