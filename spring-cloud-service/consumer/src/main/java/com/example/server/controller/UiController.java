package com.example.server.controller;

import java.util.List;

import com.example.server.domain.Person;
import com.example.server.service.MessageService;
import com.example.server.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ui")
public class UiController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private PersonService personService;

    @PostMapping("/save")
    public List<Person> save(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping(value = "/getMessage", produces={MediaType.TEXT_PLAIN_VALUE})
    public String getMessage(){
        return messageService.getMessage();
    }
}
