package fm.ua.bacs.testtaskrestservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fm.ua.bacs.testtaskrestservice.model.Contact;
import fm.ua.bacs.testtaskrestservice.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contacts")
    public List<Contact> findByRegex(@RequestParam(name = "nameFilter") String nameFilter) throws UnsupportedEncodingException {

        String filter = java.net.URLDecoder.decode(nameFilter, "UTF-8");
        System.out.println("filter = " + filter);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(contactRepository.findAll());
            System.out.println("contactRepository.findAll().stream() = " + jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<String> strings = new ArrayList<>(contactRepository.findAll().size());
        for (Object object : contactRepository.findAll()) {
            try {
                String s = mapper.writeValueAsString(object);
                if (!s.split(",")[1].split(":")[1].replace("\"", "").matches(filter)) {
                    strings.add(mapper.writeValueAsString(object));
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        List<Contact> result = new ArrayList<>();
        try {
            Contact[] contact = mapper.readValue(strings.toString(), Contact[].class);

            for (Contact c :contact ) {
                result.add(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}