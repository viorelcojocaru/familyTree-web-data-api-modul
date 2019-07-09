package com.esempla.familyTree.mongodb.repository;

import com.esempla.familyTree.mongodb.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @GetMapping("/list")
    public List<Person> getAllPerson() {
        return repository.findAll();
    }

