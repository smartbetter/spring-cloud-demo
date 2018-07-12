package com.example.server.controller;

import com.example.server.dao.PersonRepository;
import com.example.server.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Resource
    private PersonRepository personRepository;

    @PostMapping("/save")
    public List<Person> savePerson(@RequestBody Person person) {
        logger.info("person name:{}", person.getName());
        personRepository.save(person);
        return personRepository.findAll(new PageRequest(0, 50)).getContent();
    }
}
