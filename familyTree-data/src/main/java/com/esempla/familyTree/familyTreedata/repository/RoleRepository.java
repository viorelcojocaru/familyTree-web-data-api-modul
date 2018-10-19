package com.esempla.familyTree.familyTreedata.repository;

import com.esempla.familyTree.familyTreedata.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "familyTree", path = "role")
public interface RoleRepository extends JpaRepository<Role, Long> {

}
