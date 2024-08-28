package com.jordan.agendaTelefonica.controller;

import com.jordan.agendaTelefonica.domain.contact.DataCreateContact;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contacts")
public class ContactController {

    @PostMapping
    @Transactional
    public String createContact(@RequestBody DataCreateContact data) {
        System.out.println(data);
        return createContact(data);
    }
}
