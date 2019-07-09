package com.esempla.familyTree.familyTreedata.service;

import com.esempla.familyTree.familyTreedata.domain.Person;
import com.esempla.familyTree.familyTreedata.repository.PersonRepository;
import com.esempla.familyTree.familyTreedata.service.intf.PersonServiceIntf;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PersonService implements PersonServiceIntf {
    @Autowired
    private PersonRepository personRepository;
    @ApiOperation(value = "Return all persons found in relation with You")
    @Override
    public List<Person> listAll() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }
    @ApiOperation(value = "Return one persons looked by:id")
    @Override
    public Person getById(Long id) {
        Person person = personRepository.findById(id).get();
        return person;
    }

    @Override
    public Person saveOrUpdate(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }


    @Override
    public boolean existEntry(Long id) {
        return personRepository.existsById(id);
    }

    public List<Person> findPersonByFirstName(String firstname){
        return  personRepository.findPersonByFirstName(firstname);
    }


}
