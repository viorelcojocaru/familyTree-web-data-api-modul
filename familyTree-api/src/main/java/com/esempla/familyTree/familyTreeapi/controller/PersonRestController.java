package com.esempla.familyTree.familyTreeapi.controller;

import com.esempla.familyTree.familyTreedata.domain.Person;
import com.esempla.familyTree.familyTreedata.domain.PersonDto;
import com.esempla.familyTree.familyTreedata.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/person")
@Slf4j
@RequiredArgsConstructor
public class PersonRestController {

    private final PersonService personService;

    private final ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    @ResponseBody
    public List<PersonDto> list() {
        return personService.listAll().stream()
                .map(person -> convertToDto(person))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "id/{id}")
    @ResponseBody
    public PersonDto getPerson(@PathVariable("id") Long id) {
        Person person = personService.getById(id);
        if (person == null) new ResourceNotFoundException("No get found with id=" + id);
        return convertToDto(person);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create")
    public PersonDto create(@RequestBody PersonDto personDto) throws ParseException {
        Person person = convertToEntity(personDto);
        Person personCreated = personService.saveOrUpdate(person);
        return convertToDto(personCreated);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto updatePost(@RequestBody
                                        PersonDto personDto) throws ParseException {
        if (!personService.existEntry(personDto.getId()))
            new ResourceNotFoundException("No post found with id=" + personDto.getId());
        Person person = convertToEntity(personDto);
        Person personUpdated = personService.saveOrUpdate(person);
        return convertToDto(personUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        personService.delete(id);
    }

    private PersonDto convertToDto(Person person) {
        PersonDto personDto = modelMapper.map(person, PersonDto.class);
        return personDto;
    }

    private Person convertToEntity(PersonDto personDto) throws ParseException {
        Person person = modelMapper.map(personDto, Person.class);
        return person;
    }



}
