package com.jordan.agendaTelefonica.controller;

import com.jordan.agendaTelefonica.dto.CreateContactDto;
import com.jordan.agendaTelefonica.dto.ListContactDto;
import com.jordan.agendaTelefonica.dto.UpdateContactDto;
import com.jordan.agendaTelefonica.infra.excepition.ValidationExcepition;
import com.jordan.agendaTelefonica.service.ContactService;
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
    private ContactService contactService;

    @PostMapping
    @Transactional
    public ResponseEntity createContact(@RequestBody @Valid CreateContactDto data, UriComponentsBuilder uriBuilder) {
        try {
            contactService.createContact(data, uriBuilder);
            return ResponseEntity.ok().build();
        }catch (ValidationExcepition excepition) {
            return ResponseEntity.badRequest().body(excepition.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<ListContactDto>> listContact(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        Page<ListContactDto> contact = contactService.listContact(pagination);
        return ResponseEntity.ok(contact);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateContact(@RequestBody @Valid UpdateContactDto data) {
        try {
            contactService.updateContact(data);
            return ResponseEntity.ok().build();
        }catch (ValidationExcepition excepition) {
            return ResponseEntity.badRequest().body(excepition.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteContact(@PathVariable Long id) {
        try {
            contactService.deleteContact(id);
            return ResponseEntity.ok().build();
        }catch (ValidationExcepition excepition) {
            return ResponseEntity.badRequest().body(excepition.getMessage());
        }
    }
}
