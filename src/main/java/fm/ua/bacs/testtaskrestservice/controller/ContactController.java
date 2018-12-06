package fm.ua.bacs.testtaskrestservice.controller;

import fm.ua.bacs.testtaskrestservice.model.Contact;
import fm.ua.bacs.testtaskrestservice.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

//    @GetMapping("/contacts")
//    public List<Contact> getContacts() {
//        return contactRepository.findAll();
//    }

    @GetMapping("/contacts")
    public List<Contact> findByRegex(@RequestParam(name = "nameFilter") String nameFilter) {
        String pattern = "^.*[aei].*$";
        return contactRepository.findAll().stream()
                .filter(c -> !c.getName().matches("^A.*$"))
                .collect(Collectors.toList());
    }
}