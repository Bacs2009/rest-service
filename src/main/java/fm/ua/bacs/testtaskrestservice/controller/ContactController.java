package fm.ua.bacs.testtaskrestservice.controller;

import fm.ua.bacs.testtaskrestservice.model.Contact;
import fm.ua.bacs.testtaskrestservice.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactController {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/contacts")
    public List<Contact> findByRegex(@RequestParam(name = "nameFilter") String nameFilter) throws IOException {

        String filter = java.net.URLDecoder.decode(nameFilter, "UTF-8");

        List<Contact> fromDb = contactRepository.findAll();
        List<Contact> result = new ArrayList<>();

        for (long i = 1; i < fromDb.size(); i++) {
            Contact c = contactRepository.getOne(i);
            String s = String.valueOf(c);
            if (!s.split(",")[1].split("=")[1].replace("\"", "").matches(filter)) {
                result.add(c);
            }
        }

        return result;
    }
}