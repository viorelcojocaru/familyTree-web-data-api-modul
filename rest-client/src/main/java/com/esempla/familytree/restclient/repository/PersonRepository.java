package com.esempla.familytree.restclient.repository;

import com.esempla.familytree.restclient.dto.PersonDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<PersonDto, Long> {

    List<PersonDto> findPersonDtoByFirstName(String firstName);
}
