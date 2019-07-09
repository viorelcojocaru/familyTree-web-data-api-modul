package com.esempla.familyTree.familyTreedata.repository;

import com.esempla.familyTree.familyTreedata.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "familyTree", path = "person")
public interface PersonRepository extends JpaRepository<Person, Long> {
    public List<Person>  findPersonByFirstName(String firstName);
}