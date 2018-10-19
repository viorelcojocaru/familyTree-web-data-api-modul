package com.esempla.familyTree.familyTreedata.repository;

import com.esempla.familyTree.familyTreedata.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "familyTree", path = "contact")
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
