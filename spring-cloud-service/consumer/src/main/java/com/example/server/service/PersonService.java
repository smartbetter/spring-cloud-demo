package com.example.server.service;

import java.util.ArrayList;
import java.util.List;

import com.example.server.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import javax.annotation.Resource;

@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Resource
    private PersonFeignService personFeignService;

    @HystrixCommand(fallbackMethod = "fallbackSave")
    public List<Person> save(Person person) {
        logger.info("person name:{}", person.getName());
        return personFeignService.save(person);
    }

    public List<Person> fallbackSave(Person person) {
        List<Person> list = new ArrayList<Person>();
        Person p = new Person(person.getName()+"没有保存成功, Person Service 模块故障");
        list.add(p);
        return list;
    }
}
